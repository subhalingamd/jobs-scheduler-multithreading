package Trie;

public class Person {

	String Name, PhoneNumber;

    public Person(String name, String phone_number) {
    	Name=name;
    	PhoneNumber=phone_number;
    }

    public String getName() {
        return Name;
    }

     //ADDED
    @Override
    public String toString(){
        return "[Name: "+Name+", Phone="+PhoneNumber+"]";
    }
}
