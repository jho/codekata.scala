/*
 * Copyright 2010 Joshua Hollander
 */

package org.jho.codekata

/**
 * Add a method to the Int to enable extracting certain sequences of bits as an Int 
 */
class IntegerOps(val value: Int) {
    def extractBits(start: Int, end: Int) : Int = {
        val mask = (~0) >>> (32 - end - 1)
        return (value & mask) >>> start
    }
}

/**
 * Add some methods to an Array of Bytes
 */
class ByteArray(val bytes: Array[Byte]) {
    def toInt = bytes.foldLeft(0)((x, b) => (x << 8) + (b & 0xFF))
    def asString = bytes.map(0xFF & _).map { "%02x".format(_) }.foldLeft(""){_ + _}
}

object RichOpsImplicits {
    implicit def int2MyInteger(i: Int) = new IntegerOps(i)
    implicit def bytes2ByteArray(b: Array[Byte]) = new ByteArray(b)
}



