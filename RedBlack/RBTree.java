package RedBlack;


public class RBTree<T extends Comparable, E> implements RBTreeInterface<T, E>  {
	RedBlackNode<T, E> root=null;

    private void rotateLeft(RedBlackNode<T,E> pivot){
        RedBlackNode<T,E> temp= pivot.getParent();
        pivot=pivot.getRightChild();

        if (pivot.getLeftChild()!=null)
            pivot.getLeftChild().changeParent(pivot.getParent());   // Changing P's LC parent GF
        pivot.getParent().makeRightChild(pivot.getLeftChild());  // GF RC is changed
        pivot.getParent().changeParent(pivot);                   // GF's parent is parent
        pivot.makeLeftChild(pivot.getParent());
        pivot.changeParent(temp);   
        if (temp==null)
            root=pivot;
        else{
            if (pivot.getKey().compareTo(pivot.getParent().getKey())<0)
                pivot.getParent().makeLeftChild(pivot);
            else
                pivot.getParent().makeRightChild(pivot);

        }                           // P's parent is ex-GF's parent
        
    }

    private void rotateRight(RedBlackNode<T,E> pivot){
        RedBlackNode<T,E> temp= pivot.getParent();
        pivot=pivot.getLeftChild();
        

        if (pivot.getRightChild()!=null)
            pivot.getRightChild().changeParent(pivot.getParent());   // Changing P's RC parent GF
        pivot.getParent().makeLeftChild(pivot.getRightChild());  // GF LC is changed
        pivot.getParent().changeParent(pivot);  
        pivot.makeRightChild(pivot.getParent());                 // GF's parent is parent
        pivot.changeParent(temp);
        if (temp==null)
            root=pivot;  
        else{
            if (pivot.getKey().compareTo(pivot.getParent().getKey())<0)
                pivot.getParent().makeLeftChild(pivot);
            else
                pivot.getParent().makeRightChild(pivot);

        }                              // P's parent is ex-GF's parent
        
    }



    @Override
    public void insert(T key, E value) {
    	if (root==null){
    		root=new RedBlackNode(key,value,null);
    		root.setColor('B');
    		return;
    	}
    	RedBlackNode<T,E> parent=null,curr=root,uncle;
    	while (curr!=null){
    		if (curr.getKey().compareTo(key)==0){
    			curr.addData(value);
    			return;
    		}
    		parent=curr;
    		if (key.compareTo(curr.getKey())<0)
    			curr=curr.getLeftChild();
    		else
    			curr=curr.getRightChild();
    	}

    	if (key.compareTo(parent.getKey())<0){
    	  parent.makeLeftChild(new RedBlackNode<T,E>(key,value,parent));
          curr=parent.getLeftChild();
        }
    	else{
    	  parent.makeRightChild(new RedBlackNode<T,E>(key,value,parent));
          curr=parent.getRightChild();
        }


        while (curr!=root && parent!=root){

            if (parent.getKey().compareTo(parent.getParent().getKey())<0)
                uncle=parent.getParent().getRightChild();
            else 
                uncle=parent.getParent().getLeftChild();
    
            if (parent.getColor()=='R' && (uncle==null || uncle.getColor()=='B')){
                if (curr==parent.getLeftChild()){       //*L
                    if (parent==parent.getParent().getLeftChild()){     //LL                        
                        rotateRight(parent.getParent());
                        curr.getParent().setColor('B');
                        curr.getParent().getRightChild().setColor('R');
                    }
                    else{                                               //RL
                        rotateRight(parent);     //Child becomes parent
                        rotateLeft(curr.getParent());
                        curr.setColor('B');
                        curr.getLeftChild().setColor('R');
                    }
                }

                else{                                                   //*R
                    if (parent==parent.getParent().getLeftChild()){     //LR
                        rotateLeft(parent);     //Child becomes parent
                        rotateRight(curr.getParent());
                        
                        curr.setColor('B');
                        curr.getRightChild().setColor('R');
                    }
                    else{       
                        rotateLeft(parent.getParent());
                        curr.getParent().setColor('B');
                        curr.getParent().getLeftChild().setColor('R');
                    }                                                 
                }
                break; //We are done
            }

            else if (parent.getColor()=='R' && uncle.getColor()=='R'){
                    curr=curr.getParent().getParent();
                    curr.getLeftChild().setColor('B');
                    curr.getRightChild().setColor('B');
                    curr.setColor('R');
                    parent=curr.getParent();
            }

            else //No work
                break;
                
        }
    	root.setColor('B');
    }

    @Override
    public RedBlackNode<T, E> search(T key) {
    	RedBlackNode<T, E> curr=root;
    	while (curr!=null){
    		if (curr.getKey().compareTo(key)==0)
    			return curr;

    		if (key.compareTo(curr.getKey())<0)
    			curr=curr.getLeftChild();
    		else
    			curr=curr.getRightChild();
    	}
        RedBlackNode<T, E> dummy=new RedBlackNode(null,null,null);
        return dummy;
    }

    /*Not using this
    public E popValue(T key){
        RedBlackNode<T, E> curr=root;
        while (curr!=null){
            if (curr.getKey().compareTo(key)==0)
                return curr.removeData();

            if (key.compareTo(curr.getKey())<0)
                curr=curr.getLeftChild();
            else
                curr=curr.getRightChild();
        }
        return null;
    }
    */
    


}