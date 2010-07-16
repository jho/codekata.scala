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
    def testWithFullList = {
        var anagrams = Anagrams.fromFile("wordlist.txt")
        assertEquals(5683, anagrams.countAll)
        assertEquals(2531, anagrams.countGroups)
    }
}
