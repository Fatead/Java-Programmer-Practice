package JUC.atomic;

import java.util.concurrent.atomic.AtomicReference;

public class AtomicReferenceDemo {


    static class User{
        String name;
        int age;

        public User(String name,int age){
            this.age = age;
            this.name = name;
        }

    }


    public static void main(String[] args) {
        User z3 = new User("Z3",22);
        User li4 = new User("li4",23);
        AtomicReference<User> atomicReference = new AtomicReference<>();
        atomicReference.set(z3);
        System.out.println(atomicReference.compareAndSet(z3, li4));
    }
}
