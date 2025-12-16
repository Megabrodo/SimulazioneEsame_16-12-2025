package a06.e2;

import javax.swing.*;
import java.util.*;
import java.util.List;
import java.awt.*;
import java.awt.event.ActionListener;

public class GUI extends JFrame {
    
    private static final long serialVersionUID = -6218820567019985015L;
    private final Map<JButton, Pair<Integer,Integer>> cells = new HashMap<>();
    private final Logics logic;
    private boolean memoryCounter = false;
    private boolean thirdClick = false;
    private boolean equal = false;
    
    public GUI(int width) {
        this.logic = new LogicsImpl(width);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(70*width, 70*width);
        
        JPanel panel = new JPanel(new GridLayout(width,width));
        this.getContentPane().add(panel);
        
        ActionListener al = e -> {
            var jb = (JButton)e.getSource();
            if (thirdClick) {
                refresh(false);
                thirdClick = false;
            }
            
            jb.setText(Integer.toString(logic.getValueMatrix(cells.get(jb))));
            this.logic.hit(cells.get(jb));
        	if (!memoryCounter) {
                memoryCounter = true;
                return;
            }

            equal = this.logic.checkEqualsTiles(this.logic.getFirstTile(), this.logic.getSecondTile());    
            if (this.equal) {
                refresh(true);
                if (!this.logic.checkAllTiles()) {
                    this.cells.keySet()
                        .stream()
                        .forEach(entry -> entry.setEnabled(false));
                }
            } else {
                thirdClick = true;
            }

            memoryCounter = false;
        };
                
        for (int i=0; i<width; i++){
            for (int j=0; j<width; j++){
            	var pos = new Pair<>(j,i);
                final JButton jb = new JButton(pos.toString());
                this.cells.put(jb, pos);
                jb.setText("");
                jb.addActionListener(al);
                panel.add(jb);
            }
        }
        this.setVisible(true);
    }
    
    private void refresh(boolean equalTiles) {
        Pair<Integer, Integer> firstTile = this.logic.getFirstTile();
        Pair<Integer, Integer> secondTile = this.logic.getSecondTile();
        this.cells.entrySet()
            .stream()
            .filter(entry -> {
                Pair<Integer, Integer> cell = entry.getValue();
                return firstTile.equals(cell) || secondTile.equals(cell);
            })
            .forEach(entry -> {
                JButton button = entry.getKey();
                if (equalTiles) {
                    button.setEnabled(false);
                } else {
                    button.setText("");
                }
            });
        this.setVisible(true);
    }
}
