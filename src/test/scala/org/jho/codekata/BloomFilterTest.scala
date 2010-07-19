/*
 * Copyright 2010 Joshua Hollander
 */

package org.jho.codekata

import org.junit._
import Assert._
import scala.collection.mutable.HashSet
import scala.util.Random

class BloomFilterTest {
  val rand = new Random()

  @Before
  def setUp: Unit = {
  }

  @After
  def tearDown: Unit = {
  }

  @Test
  def testShortFile = {
    var filter = BloomFilter.fromFile("wordlist_short.txt", 4, 10000)
    println(filter)
    assertTrue("Filter should not be null!", filter != null)
    assertTrue("The word 'absolve' should be found", filter.check("absolve"))
    assertFalse("The word 'asdf' should not be found", !filter.check("asdf"))
  }

  @Test
  def testFullFile = {
    var filter = BloomFilter.fromFile("wordlist.txt", 4, 200000)
    println(filter)
    assertTrue("Filter should not be null!", filter != null)
    assertTrue("The word 'absolve' should be found", filter.check("absolve"))
    assertTrue("The word 'asdf' should not be found", !filter.check("asdf"))

    val words = HashSet[String]()
    //ensure that there are no false negatives (which should be impossible, if we
    //implemented this correctly)
    scala.io.Source.fromFile("wordlist.txt").getLines.foreach { line =>
      words += line
      assertTrue("False negative!!!! The word '" + line + "' should be found", filter.check(line))
    }

    //see if we get any false positives
    /*
     for(x <- 1 to 10000) {
     val word = randomWord()//rand.nextASCIIString(5)
     val found = filter.check(word)
     println(word + " was " + (if(found) "found" else "not found"))
     assertTrue(word + " should have been found in the filter",
     found || (!found && !words.contains(word)))
     }*/
  }

  /*
   def randomWord() : String = {
   val (low, high) = (97,122)
   def safeChar() = {
   rand.nextInt(high-low,low)+low;
   }
   return List.fill(""){safeChar()}.toString
   }*/
}
