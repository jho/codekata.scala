/*
 * Copyright 2010 Joshua Hollander
 */
package org.jho.codekata

import java.security.MessageDigest
import org.jho.codekata.RichOpsImplicits._

/**
 * Implementation of a BloomFilter
 *
 * @author Joshua Hollander
 */
class BloomFilter(val numHashes: Int, val numBits: Int) {
    val bits = new Array[Boolean](numBits).map(i => false)
    val hasher = new BloomHash(numHashes, numBits)

    def check(word: String): Boolean = {
        //println("Checking: " + word)
        var found = true;
        hasher.generateEach(word) { b =>
            //println("Got bit: " + b + " which is set to: " + bits(b))
            found = found && bits(b)
            //println("found = " + found)
        }
        //println("found = " + found)
        return found;
    }

    def addWord(word: String) = {
        hasher.generateEach(word.trim) { bits.update(_, true)}
    }
}

object BloomFilter {
    /**
     * Loads a BloomFilter from a text file (like /usr/dict/words)
     */
    def fromFile(fileName: String, numHashes: Int, numBits: Int) : BloomFilter = {
        val filter = new BloomFilter(numHashes, numBits)
        scala.io.Source.fromFile(fileName).getLines.foreach { line =>
            filter.addWord(line)
        }
        return filter
    }
}

 /**
  * Computes 'n' hash values for a given word for use in a BloomFilter
  */
class BloomHash(val numHashes: Int, val numBits: Int) {
    val md5 = MessageDigest.getInstance("MD5")

    def generateEach(value: String)(f: Int => Unit) {
        val digest = md5.digest(value.map(_.toByte).toArray)
        val incr = digest.length/numHashes
        for ( x <- 0 to digest.length-1 by incr) {
            f((digest.slice(x, x+incr).toInt % numBits).abs)
        }
    }
}


 
