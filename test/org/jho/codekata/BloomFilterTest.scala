/*
 * Copyright 2010 Joshua Hollander
 */

package org.jho.codekata

import org.junit._
import Assert._

class BloomFilterTest {
    @Before
    def setUp: Unit = {
    }

    @After
    def tearDown: Unit = {
    }

    @Test
    def testLoad = {
        var filter = BloomFilter.fromFile("wordlist_short.txt", 4, 1000)
        assertTrue("The word 'absolve' should be found", filter.check("absolve"))
        assertTrue("The word 'asdf' should not be found", filter.check("asdf"))
        assertTrue("Filter should not be null!", filter != null)
    }
}
