/*
 * Copyright 2010 Joshua Hollander
 */
package org.jho.codekata

import java.security.MessageDigest
import scala.collection.mutable.BitSet
import org.jho.codekata.RichOpsImplicits._

/**
 * Implementation of a BloomFilter
 *
 * @author Joshua Hollander
 */
class BloomFilter (val numHashes: Int, val numBits: Int) {
  val bits = new BitSet()
  val hasher = new BloomHash(numHashes, numBits)

  /*
  lazy val accuracy = {
  val exp = ((k:Double) * size) / width
  val probability = Math.pow(1 - Math.exp(-exp), k)

  1d - probability
  }*/

  def contains(word: String): Boolean = {
    var found = true;
    hasher.generateEach(word) { b =>
      found = found && bits(b)
    }
    return found;
  }

  def +=(word: String) : BloomFilter = {
    hasher.generateEach(word.trim)(bits += _)
    return this
  }

  override def toString() = {
    ("BloomFilter(numBits="+numBits+",numHashes="+numHashes+",bitsOn="+
     bits.size+",bitsOff="+(numBits-bits.size)+")")
  }
}

object BloomFilter {
  /**
   * Loads a BloomFilter from a text file (like /usr/dict/words)
   */
  def fromFile(fileName: String, numHashes: Int, numBits: Int) : BloomFilter = {
    val filter = new BloomFilter(numHashes, numBits)
    scala.io.Source.fromFile(fileName).getLines.foreach { line =>
      filter.add(line)
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