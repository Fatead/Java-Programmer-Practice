package generic;

public class GenericClass {

    static class Box<T>{
        private T t;

        public void add(T t){
            this.t = t;
        }

        public T get(){
            return this.t;
        }
    }

    public static void main(String[] args) {
        Box<String> box = new Box<>();
        box.add("asdasdasdasd");
        System.out.println(box.get());
    }

}
