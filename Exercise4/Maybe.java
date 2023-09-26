import java.util.function.Function;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

interface Maybe<T> {

    static <T> Maybe<T> of(T value) {
        return new Maybe<T>() {
            private final T value = value;

            @Override
            public T get() {
                return this.value;
            }

            @Override
            public boolean isEmpty() {
                return this.value == null;
            }

            @Override
            public boolean isPresent() {
                return !this.isEmpty();
            }

            @Override
            public <R> Maybe<R> map(Function<? super T, ? extends R> mapper) {
                if (this.isEmpty()) {
                    return Maybe.<R>empty();
                } else {
                    return Maybe.<R>of(mapper.apply(this.value));
                }
            }

            @Override
            public <R> Maybe<R> flatMap(Function<? super T, ? extends Maybe<? extends R>> mapper) {
                if (this.isEmpty()) {
                    return Maybe.<R>empty();
                } else {
                    Maybe<? extends R> temp = mapper.apply(this.value);
                    return Maybe.<R>of(temp.value);
                }
            }

            @Override
            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                } else if (obj instanceof Maybe<?> other) {
                    if (this.isEmpty()) {
                        return other.isEmpty();
                    } else {
                        return !other.isEmpty() && this.get().equals(other.get());
                    }
                } else {
                    return false;
                }
            }

            @Override
            public Maybe<T> filter(Predicate<? super T> pred) {
                return this.isEmpty() ? this :
                        (pred.test(this.get()) ? this : Maybe.<T>empty());
            }

            @Override
            public void ifPresent(Consumer<? super T> action) {
                if (this.isPresent()) {
                    action.accept(this.get());
                }
            }

            @Override
            public void ifPresentOrElse(Consumer<? super T> action, Runnable emptyAction) {
                if (this.isPresent()) {
                    action.accept(this.get());
                } else {
                    emptyAction.run();
                }
            }

            @Override
            public T orElse(T alternative) {
                return value != null ? value : alternative;
            }

            @Override
            public T orElseGet(Supplier<? extends T> other) {
                return value != null ? value : other.get();
            }

            @Override
            public Maybe<T> or(Supplier<? extends Maybe<? extends T>> supplier) {
                return this.isPresent() ? this : supplier.get().map(Function.identity());
            }

            @Override
            public String toString() {
                if (this.value == null) {
                    return "Maybe.empty";
                } else {
                    return "Maybe[" + this.value + "]";
                }
            }
        };
    }

    static <T> Maybe<T> empty() {
        return new Maybe<T>(null);
    }

    T get();

    boolean isEmpty();

    boolean isPresent();

    <R> Maybe<R> map(Function<? super T, ? extends R> mapper);

    <R> Maybe<R> flatMap(Function<? super T, ? extends Maybe<? extends R>> mapper);

    boolean equals(Object obj);

    Maybe<T> filter(Predicate<? super T> pred);

    void ifPresent(Consumer<? super T> action);

    void ifPresentOrElse(Consumer<? super T> action, Runnable emptyAction);
}



