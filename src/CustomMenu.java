import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by d853693 on 1/9/2017.
 */
public class CustomMenu
{
    private JFrame myGUIFrame;
    Container contents;
    Font defaultFont = new Font((new JLabel()).getFont().getName(), Font.PLAIN, 16);

    JComboBox programSelectBox;
    JTextArea programDescription;

    Box imageSelectAreaOne;
    JTextField imageOneLink;
    JLabel inputLabelOne;
    Box inputThumbDisplayOne;
    JButton inputOneBrowseButton;
    JButton inputOneLoadButton;

    Box imageSelectAreaTwo;
    JTextField imageTwoLink;
    JLabel inputLabelTwo;
    Box inputThumbDisplayTwo;
    JButton inputTwoBrowseButton;
    JButton inputTwoLoadButton;

    Box outputSelectArea;
    JTextField imageOutputLink;
    JLabel outputLabel;
    Box outputDisplayArea;
    JButton outputBrowseButton;
    JButton outputSaveButton;

    Box additionalControlsArea;//bottom 200 pixels

    File inputFileOne;
    File inputFileTwo;
    File outputFile;

    Thumbnail thumbOne;
    Thumbnail thumbTwo;
    Thumbnail thumbOut;

    BufferedImage imgOne;
    BufferedImage imgTwo;
    BufferedImage imgOut;

    public static void main(String[] args)
    {
        CustomMenu menu = new CustomMenu();
    }
    public static File chooseAFile()
    {
        FileDialog chooser = new java.awt.FileDialog(
                (java.awt.Frame) null);
        chooser.setVisible(true);
        String filePath = chooser.getDirectory() + chooser.getFile();

        return new File(filePath);
    }

    public CustomMenu()
    {
        JLabel versionInfo = new JLabel("Version: 0.1 (Alpha)");
        String[] programList = {"Pad Image Borders", "Overlay (Green Screen Replacement)",
                                    "Average Images", "Invert Colors", "Add Image", "Subtract Image"};


        myGUIFrame = new JFrame("Caspian's Image Editing Program");
        int frameHight = 700;
        int frameWidth = 1200;

        Dimension mainWindowDefaultSize = new Dimension(frameWidth, frameHight);


        myGUIFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myGUIFrame.setSize(mainWindowDefaultSize);
        myGUIFrame.setVisible(true);

        contents = Box.createVerticalBox();
        contents.setFont(defaultFont);

        //Making the upper box With program descripion, program selection, and version info

        Box upperContentBox = this.buildUpperContentBox(versionInfo, programList);

        //Lower Content Box - this is the box where things change
        Box middleContentBox = buildMiddleContentBox();

        //End Lower Content Box


        //additionalControlsArea
        additionalControlsArea = Box.createHorizontalBox();
        additionalControlsArea.setMaximumSize(new Dimension(1200,200));
        additionalControlsArea.add(Box.createGlue());
        //endAdditionalControlsArea

        contents.add(Box.createVerticalStrut(10));
        contents.add(upperContentBox);
        contents.add(Box.createVerticalStrut(10));
        contents.add(middleContentBox);
        contents.add(Box.createVerticalStrut(10));
        contents.add(additionalControlsArea);
        contents.add(Box.createVerticalStrut(10));




        myGUIFrame.add(contents);
        myGUIFrame.repaint();
        myGUIFrame.revalidate();
    }

    private class ProgramChangeHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent theAction)
        {
            if (theAction.getActionCommand().equals("Load Program"))
            {
                System.out.println(programSelectBox.getSelectedItem());
                programDescription.setText(programSelectBox.getSelectedItem().toString());
            }
        }
    }

    private class InputOneButtonHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent theAction)
        {
            if (theAction.getActionCommand().equals("Browse"))
            {
                inputFileOne = CustomMenu.chooseAFile();
                if (inputFileOne != null && inputFileOne.getAbsoluteFile() != null)
                {
                    imageOneLink.setText(inputFileOne.getAbsolutePath());
                }
            }

            if (theAction.getActionCommand().equals("Load"))
            {
                boolean found = false;
                if ((inputFileOne == null || !imageOneLink.getText().equals(inputFileOne.getAbsolutePath())))
                {
                    try
                    {
                        inputFileOne = new File(imageOneLink.getText());
                        found = true;
                    }
                    catch (NullPointerException err)
                    {
                        inputLabelOne.setForeground(Color.RED);
                        inputLabelOne.setText("* File not found *");
                    }

                }
                else
                {
                    found = true;
                }
                if (found)
                {
                    try
                    {
                        Dimension bounds = inputThumbDisplayOne.getBounds().getSize();
                        imgOne = ImageIO.read(inputFileOne);
                        if (thumbOne == null)
                        {
                            thumbOne = new Thumbnail(imgOne);
                            thumbOne.setNewIconKeepRatio(imgOne, bounds);
                            inputThumbDisplayOne.add(thumbOne);
                        }
                        else
                        {
                            boolean success = thumbOne.setNewIconKeepRatio(imgOne, bounds);
                            if (!success)
                            {
                                throw (new IOException());
                            }
                        }
                        inputLabelOne.setForeground(Color.BLACK);
                        inputLabelOne.setText("* Load Successful *");
                        myGUIFrame.repaint();
                        myGUIFrame.revalidate();
                    }
                    catch (IOException err)
                    {
                        inputLabelOne.setForeground(Color.RED);
                        inputLabelOne.setText("* There was an error *");
                    }


                }
            }
        }
    }


    //
    public Box buildUpperContentBox(JLabel versionInfo, String[] programList)
    {
        Box upperContentBox = Box.createHorizontalBox();
        upperContentBox.setMaximumSize(new Dimension(1200,150));

        programDescription = new JTextArea();
        programDescription.setText("No program loaded\r\n" +
                "Select a program from the box on the right and click \"Load Program\"");
        programDescription.setBackground(Color.LIGHT_GRAY);
        programDescription.setEditable(false);
        programDescription.setFont(defaultFont);
        JScrollPane progDescScrollPane = new JScrollPane(programDescription);



        programSelectBox = new JComboBox();
        Dimension buttonSize = new Dimension(300, 40);
        programSelectBox.setMaximumSize(buttonSize);
        programSelectBox.setFont(defaultFont);
        programSelectBox.setBackground(Color.LIGHT_GRAY);
        for (int index = 0; index < programList.length; index++)
        {
            programSelectBox.addItem(programList[index]);
        }
        JButton programChangeButton = new JButton("Load Program");
        programChangeButton.addActionListener(new ProgramChangeHandler());

        Box selectionOuterBox = Box.createVerticalBox();
        selectionOuterBox.add(programSelectBox);
        selectionOuterBox.add(Box.createVerticalStrut(10));
        selectionOuterBox.add(programChangeButton);
        selectionOuterBox.setMaximumSize(new Dimension(270, 150));

        Box versionInfoBox = Box.createVerticalBox();
        JLabel createdBy = new JLabel("Created by Caspian Peavyhouse");

        versionInfoBox.add(versionInfo);
        versionInfoBox.add(createdBy);
        versionInfoBox.add(Box.createVerticalGlue());

        upperContentBox.add(Box.createHorizontalStrut(10));
        upperContentBox.add(progDescScrollPane);
        upperContentBox.add(Box.createHorizontalStrut(10));
        upperContentBox.add(selectionOuterBox);
        upperContentBox.add(Box.createHorizontalStrut(10));
        upperContentBox.add(versionInfoBox);
        upperContentBox.add(Box.createHorizontalStrut(10));
        //end upper content box

        return upperContentBox;
    }

    public Box buildMiddleContentBox()
    {
        Box middleContentBox = Box.createVerticalBox();

        Box upperMiddleContentBox = buildUpperMiddleContentBox();

        Box lowerLowerContentBox = Box.createHorizontalBox();



        middleContentBox.add(Box.createVerticalStrut(10));
        middleContentBox.add(upperMiddleContentBox);
        middleContentBox.add(Box.createVerticalStrut(10));
        middleContentBox.add(lowerLowerContentBox);
        middleContentBox.add(Box.createVerticalStrut(10));

        return middleContentBox;
    }

    public Box buildUpperMiddleContentBox()
    {
        Box upperMiddleContentBox = Box.createHorizontalBox();


        //upperMiddleContentBox - box where the primary files are selected
        imageSelectAreaOne = Box.createVerticalBox();
        imageSelectAreaOne.setMaximumSize(new Dimension(380,200));

        imageOneLink = new JTextField();
        imageOneLink.setMaximumSize(new Dimension(380, 40));
        imageOneLink.setFont(defaultFont);
        Box imageOneButtonBox = Box.createHorizontalBox();
        inputOneBrowseButton = new JButton("Browse");
        inputOneLoadButton = new JButton("Load");

        InputOneButtonHandler oneButtonHandler = new InputOneButtonHandler();

        inputOneBrowseButton.addActionListener(oneButtonHandler);
        inputOneLoadButton.addActionListener(oneButtonHandler);

        imageOneButtonBox.setMaximumSize(new Dimension(380, 40));
        imageOneButtonBox.add(Box.createHorizontalStrut(10));
        imageOneButtonBox.add(inputOneBrowseButton);
        imageOneButtonBox.add(Box.createHorizontalStrut(10));
        imageOneButtonBox.add(inputOneLoadButton);
        imageOneButtonBox.add(Box.createHorizontalStrut(10));

        inputLabelOne = new JLabel();
        inputLabelOne.setMaximumSize(new Dimension(380, 40));
        inputLabelOne.setFont(defaultFont);

        imageSelectAreaOne.add(imageOneLink);
        imageSelectAreaOne.add(Box.createVerticalStrut(10));
        imageSelectAreaOne.add(imageOneButtonBox);
        imageSelectAreaOne.add(Box.createVerticalStrut(10));
        imageSelectAreaOne.add(inputLabelOne);
        imageSelectAreaOne.add(Box.createVerticalGlue());
        //end imageSelectArea

        //inputThumbDisplayOne
        inputThumbDisplayOne = Box.createVerticalBox();
        inputThumbDisplayOne.setMaximumSize(new Dimension(200, 400));
        inputThumbDisplayOne.add(Box.createGlue());
        //end inputThumbDisplayOne

        //outputSelectArea
        outputSelectArea = Box.createVerticalBox();
        outputSelectArea.setMaximumSize(new Dimension(380,200));

        imageOutputLink = new JTextField();
        imageOutputLink.setMaximumSize(new Dimension(380, 40));
        imageOutputLink.setFont(defaultFont);
        Box outputButtonBox = Box.createHorizontalBox();
        outputBrowseButton = new JButton("Browse");
        outputSaveButton = new JButton("Load");

        outputButtonBox.setMaximumSize(new Dimension(380, 40));
        outputButtonBox.add(Box.createHorizontalStrut(10));
        outputButtonBox.add(outputBrowseButton);
        outputButtonBox.add(Box.createHorizontalStrut(10));
        outputButtonBox.add(outputSaveButton);
        outputButtonBox.add(Box.createHorizontalStrut(10));

        outputSelectArea.add(imageOutputLink);
        outputSelectArea.add(Box.createVerticalStrut(10));
        outputSelectArea.add(outputButtonBox);
        outputSelectArea.add(Box.createVerticalGlue());
        //end outputSelectArea



        upperMiddleContentBox.add(Box.createHorizontalStrut(10));
        upperMiddleContentBox.add(imageSelectAreaOne);
        upperMiddleContentBox.add(Box.createHorizontalStrut(10));
        upperMiddleContentBox.add(inputThumbDisplayOne);
        upperMiddleContentBox.add(Box.createHorizontalStrut(10));
        upperMiddleContentBox.add(outputSelectArea);
        upperMiddleContentBox.add(Box.createHorizontalStrut(10));

        //end upperMiddleContentBox
        return upperMiddleContentBox;
    }


    //private Box buildLowerMiddleContentBox()
    {
        Box lowerMiddleContentbox = Box.createHorizontalBox();



    }
}
