/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jho.codekata

import org.junit._
import Assert._
import org.jho.codekata.RichOpsImplicits._

class RichOpsTest {

    @Before
    def setUp: Unit = {
    }

    @After
    def tearDown: Unit = {
    }

    @Test
    def testExtractBits = {
        assertEquals((0x601f.extractBits(12, 14)), 6)
        assertEquals((0x601f.extractBits(0, 2)), 7)
    }

    @Test
    def testByteArray = {
        val bytes = new Array[Byte](1)
        bytes(0) = 0xFF.toByte
        assertTrue("0xFF should be 255", bytes.toInt == 255)
        assertTrue("0xFF should be the string 'ff'", bytes.asString.equals("ff"))
    }
}
