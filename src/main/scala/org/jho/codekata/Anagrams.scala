/*
 * Copyright 2009 Joshua Hollander.
 */

package org.jho.codekata

import scala.collection.mutable.HashMap
import scala.collection.mutable.ListBuffer

class Anagrams {
  var words = new HashMap[String, ListBuffer[String]]

  /***
   * Sort the letters in the word alphabetically and then use
   * them as the key to a hash table.  Each entry should be a list of
   * words that are anagrams of each other.  Can be done as
   * the words are added to the set. Super simple.
   *
   * Eg: eilnst => enlist, inlets, listen, silent
   *
   */
  def addWords(toAdd: Iterator[String]) {
    toAdd.foreach{ word =>
      var sorted = word.toLowerCase.trim.sortWith((c1,c2) => c1 < c2 )
      //turns out ListBuffer (with appends) was faster
      //than List (with prepends)
      if (!words.contains(sorted))
        words += sorted -> new ListBuffer()
      words(sorted) += word
    }
  }

  def findForWord(word: String)
  {
    var sorted = word.toLowerCase.trim.sortWith((c1,c2) => c1 < c2 )
    if ( words.contains(sorted) && words(sorted).size > 1)
      println(words(sorted).mkString(" "))
    else
      println("No anagrams found for " + word)
  }

  def findAll() {
    words.keys.foreach{ key =>
      if ( words(key).size > 1)
        println(words(key).mkString(" "))
    }
  }

  def countAll() : Int = {
    words.values.filter(a => a.size > 1).foldLeft(0)(_ + _.size)
  }

  def countGroups() : Int = {
    words.values.filter(a => a.size > 1).size
  }
}

object Anagrams {
  def fromFile(fileName: String) : Anagrams ={
    var anagrams = new Anagrams
    anagrams.addWords(scala.io.Source.fromFile(fileName).getLines)
    return anagrams
  }
}
