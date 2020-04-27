package br.com.codenation.Util;

import java.util.Comparator;
import java.util.Objects;
import java.util.function.Function;

public final class ComparadorDesempateReverso {
  public static <T, U extends Comparable<? super U>, K extends Comparable<? super K>>
      Comparator<T> compare(
          Function<? super T, ? extends U> extrator,
          Function<? super T, ? extends K> desempateExtrator) {
    Objects.requireNonNull(extrator);
    Objects.requireNonNull(desempateExtrator);

    return (c1, c2) -> {
      int comp = extrator.apply(c1).compareTo(extrator.apply(c2));
      if (comp == 0) comp = desempateExtrator.apply(c2).compareTo(desempateExtrator.apply(c1));
      return comp;
    };
  }
}
