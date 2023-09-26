import java.util.function.Function;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

class Maybe<T> {
    private final T value;

    private Maybe(T value) {
        this.value = value;
    }

    static <U> Maybe<U> of(U value) {
        return new Maybe<U>(value);
    }

    static <T> Maybe<T> empty() {
        return new Maybe<T>(null);
    }

    private T get() {
        return this.value;
    }

    private boolean isEmpty() {
        return this.value == null;
    }

    private boolean isPresent() {
        return !this.isEmpty();
    }

    <R> Maybe<R> map(Function<? super T, ? extends R> mapper) {
        if (this.isEmpty()) {
            return Maybe.<R>empty();
        } else {
            return Maybe.<R>of(mapper.apply(this.value));
        }
    }

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

    public Maybe<T> filter(Predicate<? super T> pred) {
        return this.isEmpty() ? this :
            (pred.test(this.get()) ? this : Maybe.<T>empty());
    }                                                                           

    public void ifPresent(Consumer<? super T> action) { 
        if (this.isPresent()) {
            action.accept(this.get());
        }
    }

    public void ifPresentOrElse(Consumer<? super T> action, Runnable emptyAction) {
        if (this.isPresent()) {
            action.accept(this.get());
        } else {
            emptyAction.run();
        }
    }

    public T orElse(T alternative) {
        return value != null ? value : alternative;
    } 

    public T orElseGet(Supplier<? extends T> other) {
        return value != null ? value : other.get();
    }

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
}
                                                
