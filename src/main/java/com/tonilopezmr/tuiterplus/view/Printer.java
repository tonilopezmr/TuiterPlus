package com.tonilopezmr.tuiterplus.view;

public interface Printer<T> {
  void print();
  void load(T printable);
}
