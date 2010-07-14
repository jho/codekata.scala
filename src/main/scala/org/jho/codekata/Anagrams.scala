/*
 * Copyright 2009 Joshua Hollander.
 */

package org.jho.codekata

import scala.collection.mutable.HashMap

class Anagrams {
    var words = new HashMap[String, List[String]]

    /***
     * Sort the letters in the word alphabetically and then use
     * them as the key to a hash table.  Each entry should be a list of
     * words that are anagrams of each other.  Can be done as
     * the words are added to the set. Super simple.
     *
     * eilnst => enlist, inlets, listen, silent
     *
     */
    def addWord(word: String) {
        var sorted = word.sortWith((c1,c2) => c1 < c2 )
        if ( words.contains(sorted))
            words.update(sorted, word :: words(sorted))
        else
            words += sorted -> List(word)
    }

    def findForWord(word: String)
    {
        var sorted = word.sortWith((c1,c2) => c1 < c2 )
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

    /*
    def countForWord() : Int {
       words.
       return 0
    }

    def countAll() : Int {

    }*/
}

object Anagrams {
    def fromFile(fileName: String) : Anagrams ={
        var anagrams = new Anagrams
        scala.io.Source.fromFile(fileName).getLines.foreach(word => anagrams.addWord(word))
        return anagrams
    }
}
