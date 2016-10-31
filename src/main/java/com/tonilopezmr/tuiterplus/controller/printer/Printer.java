package com.tonilopezmr.tuiterplus.controller.printer;

/**
 * How a object it's printable
 *
 * @param <T> Object to print
 */
public interface Printer<T> {
  void print();
  void load(T printable);
}
