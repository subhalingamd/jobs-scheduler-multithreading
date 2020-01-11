package Trie;

import java.util.ArrayList;

public class Trie<T> implements TrieInterface {
    TrieNode<T> root=new TrieNode();



    @Override
    public boolean delete(String word) {
        TrieNode<T> last=root,curr=root;
        char nextLast=word.charAt(0);
        for (int i=0;i<word.length();i++){
            if (curr.getChild(word.charAt(i))==null){
                return false;
            }
            if (curr.getValue()!=null || curr.hasChildOtherThan(word.charAt(i))){
                last=curr;
                nextLast=word.charAt(i);
            }
            curr=curr.getChild(word.charAt(i));
        }
        if (curr==null || curr.getValue()==null)
            return false;
        

        if (curr.hasChild())
            curr.removeValue();
        else{
            last.killChild(nextLast);
        }
        return true;
    }

    @Override
    public TrieNode search(String word) {
        TrieNode<T> curr=root;
        for (int i=0;i<word.length();i++){
            if (curr.getChild(word.charAt(i))==null)
                return null;
            curr=curr.getChild(word.charAt(i));
        }
        if (curr==null || curr.getValue()==null)
            return null;

        return curr;
    }

    @Override
    public TrieNode startsWith(String prefix) {
        TrieNode<T> curr=root;
        for (int i=0;i<prefix.length();i++){
            if (curr.getChild(prefix.charAt(i))==null)
                return null;
            curr=curr.getChild(prefix.charAt(i));
        }
        return curr;
    }


    @Override
    public void printTrie(TrieNode trieNode){
        if (trieNode==null){
            return;
        }
        if (trieNode.getValue()!=null){
            System.out.println(trieNode.getValue());
        }
        for (int i=0;i<95;i++){
            printTrie(trieNode.getChildByIndex(i));
        }

    }

    @Override
    public boolean insert(String word, Object value) {
        TrieNode<T> curr=root;
        for (int i=0;i<word.length();i++){
            if (curr.getChild(word.charAt(i))==null)
                curr.makeChild(word.charAt(i));
            curr=curr.getChild(word.charAt(i));
        }
        if (curr.getValue()!=null)
            return false;
        curr.setValue((T)value);
        return true;
    }

    @Override
    public void printLevel(int level) {
        ArrayList<TrieNode<T>> t1=new ArrayList(),t2=new ArrayList();
        ArrayList<Character> Level=new ArrayList();
        int x=1;
        System.out.print("Level "+level+": ");
        t2.add(root);
        do{
            Level=new ArrayList();
            
            t1=t2;
            t2=new ArrayList();
            for (int j=0;j<95;j++){
            //    boolean flag=true;
              for (int i=0;i<t1.size();i++){
                
                    TrieNode<T> child=t1.get(i).getChildByIndex(j);
                    if (child!=null){   
                        t2.add(child);
                        if (j!=0){
            //            if (flag && j!=0){
                            Level.add(new Character((char)(j+32)));
            //                flag=false;
                        }
                    }
                }
            }
            
            
            x++;
        }while (t2.size()>0 && x<=level);

        if (x<=level){
            System.out.println("NOT FOUND");
            return;
        }

            int y;
            for (y=0;y<Level.size()-1;y++){
                System.out.print(Level.get(y)+",");
            }
            if (y==Level.size()-1)
                System.out.print(Level.get(y));
            System.out.println();


    }

    @Override
    public void print() {
        System.out.println("-------------");
        System.out.println("Printing Trie");
        ArrayList<TrieNode<T>> t1=new ArrayList(),t2=new ArrayList();
        ArrayList<Character> Level=new ArrayList();
        int x=1;
        t2.add(root);
        do{
            System.out.print("Level "+x+": ");
            t1=t2;
            t2=new ArrayList();
            for (int j=0;j<95;j++){ //j=1 directly?
            //    boolean flag=true;
              for (int i=0;i<t1.size();i++){
                
                    TrieNode<T> child=t1.get(i).getChildByIndex(j);
                    if (child!=null){   
                        t2.add(child);
                        if (j!=0){
            //            if (flag && j!=0){
                            Level.add(new Character((char)(j+32)));
            //                flag=false;
                        }
                    }
                }
            }
            int y;
            for (y=0;y<Level.size()-1;y++){
                System.out.print(Level.get(y)+",");
            }
            if (y==Level.size()-1)
                System.out.print(Level.get(y));
            Level=new ArrayList();
            x++;
            System.out.println();
        }while (t2.size()>0);
        System.out.println("-------------");
    }
}