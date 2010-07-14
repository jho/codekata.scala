/*
 * Copyright 2009 Joshua Hollander.
 */

package org.jho.codekata

import org.junit._
import Assert._

class AnagramsTest {

    @Before
    def setUp: Unit = {
    }

    @After
    def tearDown: Unit = {
    }

    @Test
    def testWithShortList = {
        var anagrams = Anagrams.fromFile("wordlist_short.txt")
        anagrams.findAll()
    }
    
    @Test
    def testWithFullList = {
        var anagrams = Anagrams.fromFile("wordlist.txt")
        anagrams.findAll()
    }
}
