/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTextField;

/**
 *
 * @author Malith
 */
public class AutoComboBox extends JComboBox<Object> {

    //String[] keyWord;
    String keyWord[] = {"item1", "item2", "item3"};
    Vector myVector = new Vector();

    public AutoComboBox() {

        setModel(new DefaultComboBoxModel(myVector));
        setSelectedIndex(-1);
        setEditable(true);
        JTextField text = (JTextField) this.getEditor().getEditorComponent();
        text.setFocusable(true);
        text.setText("");
        text.addKeyListener(new ComboListener(this, myVector));
        setMyVector();
    }

    /**
     * set the item list of the AutoComboBox
     * @param keyWord an String array
     */
    public void setKeyWord(String[] keyWord) {
        this.keyWord = keyWord;
        setMyVectorInitial();
    }

    private void setMyVector() {
        int a;
        for (a = 0; a < keyWord.length; a++) {
            myVector.add(keyWord[a]);
        }
    }

    private void setMyVectorInitial() {
        myVector.clear();
        int a;
        for (a = 0; a < keyWord.length; a++) {

            myVector.add(keyWord[a]);
        }
    }

}
