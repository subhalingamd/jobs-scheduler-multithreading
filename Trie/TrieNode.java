package Trie;


import Util.NodeInterface;
import java.util.ArrayList;


public class TrieNode<T> implements NodeInterface<T> {
	ArrayList<TrieNode<T>> child=new ArrayList(95);
	T value;

	public TrieNode(){
		for (int i=0;i<95;i++)
			child.add(null);
		this.value=null;
	}

	public void setValue(T value){
		this.value=value;
	}

	public TrieNode<T> getChild(char ch){
		return child.get(((int)ch)-32);
	}

	public void makeChild(char ch){
		child.set(((int)ch)-32,new TrieNode<T>());
	}

	public boolean hasChild(){
		for (int i=0;i<95;i++)
			if (child.get(i)!=null)
				return true;
		return false;
	}

	public boolean hasChildOtherThan(char ch){
		for (int i=0;i<95;i++)
			if (child.get(i)!=null && (int)ch!=i+32)   //RECHECK THIS
				return true;
		return false;
	}

	public void killChild(char ch){
		if (child.get(((int)ch)-32)==null)
			System.out.println("\t>>>");
		child.set(((int)ch)-32,null);
	}

	public void removeValue(){
		this.value=null;
	}

	public TrieNode getChildByIndex(int i){
		return child.get(i);
	}

    @Override
    public T getValue() {
        return value;
    }


}