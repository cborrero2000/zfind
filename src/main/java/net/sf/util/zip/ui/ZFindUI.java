package net.sf.util.zip.ui;

import com.ezware.dialog.task.TaskDialog;
import com.ezware.dialog.task.TaskDialogs;
import net.sf.util.zip.ZipFinder;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: dattadi
 * Date: 5/25/12
 * Time: 8:35 PM
 * To change this template use File | Settings | File Templates.
 */
public class ZFindUI extends JFrame{

    public ZFindUI(){
        initComponents();
    }


    private void initComponents() {

        searchModebuttonGroup = new javax.swing.ButtonGroup();
        inputPanel = new javax.swing.JPanel();
        chosenFileTextField = new javax.swing.JTextField();
        fileChooserButton = new javax.swing.JButton();
        resultDisplayScrollPane = new javax.swing.JScrollPane();
        resultDisplayTextArea = new javax.swing.JTextArea();
        fileNameLabel = new javax.swing.JLabel();
        fileNameTextField = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        normalSearchRadioButton = new javax.swing.JRadioButton();
        regexRadioButton = new javax.swing.JRadioButton();
        matchCaseCheckBox = new javax.swing.JCheckBox();
        startScanjButton = new javax.swing.JButton();
        copyResToFilejButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        inputPanel.setBackground(new java.awt.Color(204, 204, 255));
        inputPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Choose input parameters"));

        chosenFileTextField.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N

        fileChooserButton.setText("Choose directory or archive");
        fileChooserButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileChooserButtonActionPerformed(evt);
            }
        });

        resultDisplayScrollPane.setBorder(javax.swing.BorderFactory.createTitledBorder("Search result"));
        resultDisplayScrollPane.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N

        resultDisplayTextArea.setColumns(20);
        resultDisplayTextArea.setRows(5);
        resultDisplayScrollPane.setViewportView(resultDisplayTextArea);

        fileNameLabel.setText(" File name to search");

        fileNameTextField.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        fileNameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fielNameTextFieldActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Search Mode"));

        searchModebuttonGroup.add(normalSearchRadioButton);
        normalSearchRadioButton.setSelected(true);
        normalSearchRadioButton.setText("Normal Search");


        searchModebuttonGroup.add(regexRadioButton);
        regexRadioButton.setText("Regular Expression");

        matchCaseCheckBox.setText("Ignore Case");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(normalSearchRadioButton)
                                        .addComponent(regexRadioButton))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 170, Short.MAX_VALUE)
                                .addComponent(matchCaseCheckBox)
                                .addGap(33, 33, 33))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(matchCaseCheckBox))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(normalSearchRadioButton)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(regexRadioButton)))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        startScanjButton.setText("Start scan");
        startScanjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startScanjButtonActionPerformed(evt);
            }
        });

        copyResToFilejButton.setText("Save result to file");
        copyResToFilejButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                copyResToFilejButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout inputPanelLayout = new javax.swing.GroupLayout(inputPanel);
        inputPanel.setLayout(inputPanelLayout);
        inputPanelLayout.setHorizontalGroup(
                inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(inputPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(inputPanelLayout.createSequentialGroup()
                                                .addComponent(resultDisplayScrollPane)
                                                .addContainerGap())
                                        .addGroup(inputPanelLayout.createSequentialGroup()
                                                .addGroup(inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addGroup(inputPanelLayout.createSequentialGroup()
                                                                        .addComponent(chosenFileTextField)
                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                        .addComponent(fileChooserButton))
                                                                .addGroup(inputPanelLayout.createSequentialGroup()
                                                                        .addComponent(fileNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                        .addComponent(fileNameTextField)))
                                                        .addComponent(startScanjButton, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(28, 28, 28))
                                        .addGroup(inputPanelLayout.createSequentialGroup()
                                                .addComponent(copyResToFilejButton)
                                                .addGap(0, 0, Short.MAX_VALUE))))
        );
        inputPanelLayout.setVerticalGroup(
                inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(inputPanelLayout.createSequentialGroup()
                                .addGroup(inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(chosenFileTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(fileChooserButton, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(11, 11, 11)
                                .addGroup(inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(fileNameLabel)
                                        .addComponent(fileNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(8, 8, 8)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(11, 11, 11)
                                .addComponent(startScanjButton)
                                .addGap(11, 11, 11)
                                .addComponent(resultDisplayScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(copyResToFilejButton)
                                .addContainerGap(35, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(inputPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(inputPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>


    private void fielNameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void fileChooserButtonActionPerformed(java.awt.event.ActionEvent evt) {
        final JFileChooser fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        int returnVal = fc.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            chosenFileTextField.setText(file.getAbsolutePath());
        }
    }

    private void startScanjButtonActionPerformed(java.awt.event.ActionEvent evt) {
        resultDisplayTextArea.setText("");
        final File chosenFile=new File(chosenFileTextField.getText());
        try{
            
            if(!chosenFile.exists()){
                JOptionPane.showMessageDialog(this, "Target lookup directory or archive file does not exists!");
                chosenFileTextField.setText("");
                return;
            }

            final String fileName=fileNameTextField.getText().trim();
            if(fileName.equals("")){
                JOptionPane.showMessageDialog(this, "File name can not be empty!");
                fileNameTextField.setText("");
                return;
            }

            final boolean ignoreCase=matchCaseCheckBox.isSelected();
            final boolean regularExpression=regexRadioButton.isSelected();

            SwingUtilities.invokeLater(new Runnable()
            {
                public void run()
                {
                    try {
                        ZipFinder zFind = new ZipFinder(chosenFile);
                        zFind.setIgnoreCase(ignoreCase);
                        zFind.setRegularExpression(regularExpression);
                        zFind.setSearchFileName(fileName);
                        List<String> matchedList=zFind.getMatchedEntries ();

                        if(matchedList.size()==0){
                            addResultString("[0] matching entries found.");
                        }else{
                            addResultString("["+matchedList.size()+"] matching entries found."); 
                            for(String entry:matchedList)
                                addResultString(entry);
                        }
                    } catch (IOException e) {
                        TaskDialogs.showException(e);
                    }
                }
            });
            
        }catch (Throwable t){
            TaskDialogs.showException(t);
        }

    }

    private void addResultString(String str){
       resultDisplayTextArea.append(str+"\n");
    }
    
    private void copyResToFilejButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    // Variables declaration - do not modify
    private javax.swing.JTextField chosenFileTextField;
    private javax.swing.JButton copyResToFilejButton;
    private javax.swing.JTextField fileNameTextField;
    private javax.swing.JButton fileChooserButton;
    private javax.swing.JLabel fileNameLabel;
    private javax.swing.JPanel inputPanel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JCheckBox matchCaseCheckBox;
    private javax.swing.JRadioButton normalSearchRadioButton;
    private javax.swing.JRadioButton regexRadioButton;
    private javax.swing.JScrollPane resultDisplayScrollPane;
    private javax.swing.JTextArea resultDisplayTextArea;
    private javax.swing.ButtonGroup searchModebuttonGroup;
    private javax.swing.JButton startScanjButton;
    // End of variables declaration



}
