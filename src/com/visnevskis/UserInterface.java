package com.visnevskis;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserInterface extends JFrame {
    int numberOfDistricts;
    CardLayout cards = new CardLayout();
    GridLayout grid = new GridLayout(5, 1);
    boolean errorMessage = false;
    boolean errorMessage2 = false;
    District disc;

    JPanel pane1 = new JPanel();
    JPanel pane2 = new JPanel();
    JPanel pane3 = new JPanel();

    DistrictList districtList = new DistrictList();
    GetDistrictFactory districtFactory = new GetDistrictFactory();

    public UserInterface(){
        super("RouteMaker");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();


        //City number input
        JLabel text = new JLabel("Hello, this program helps you make a route for your food truck.");
        JLabel text2 = new JLabel("Enter how many districts you wish to visit:");
        JTextField cityNumber = new JTextField();
        JButton next = new JButton("Next");
        next.addActionListener(e -> {
            try{
                numberOfDistricts = Integer.parseInt(cityNumber.getText());
                cards.show(mainPanel, "pane2");
            } catch (Exception exception){
                if(!errorMessage){
                    JLabel error = new JLabel("Bad input");
                    pane1.add(error);
                    revalidate();
                    repaint();
                    errorMessage = true;
                }
            }
        });
        pane1.setLayout(grid);
        pane1.add(text);
        pane1.add(text2);
        pane1.add(cityNumber);
        pane1.add(next);

        //get city names
        JLabel cityText = new JLabel("You can travel through these districts:");
        JLabel cityText2 = new JLabel("Verkiai, Zirmunai, Antakalnis, Fabijoniskes. Enter district name:");

        JTextField districtName = new JTextField();
        JButton next2 = new JButton("Next");
        next2.addActionListener(e -> {
            try{
                if(districtFactory.getDistrict(districtName.getText()) == null){
                        districtName.setText("");
                        throw new Exception();
                }
                District newDisc = districtFactory.getDistrict(districtName.getText());
                newDisc.getName();
                newDisc.getResidents();
                newDisc.getAveragePrice();
                districtList.addDistrict(newDisc);
                districtName.setText("");
                if(districtList.getDistrictSize() == numberOfDistricts){
                    initializeCard3();
                    cards.show(mainPanel, "pane3");
                }

            } catch (Exception exception){
                if(!errorMessage2){
                    JLabel error = new JLabel("Bad input");
                    pane2.add(error);
                    revalidate();
                    repaint();
                    errorMessage2 = true;
                }
            }
        });
        pane2.setLayout(grid);
        pane2.add(cityText);
        pane2.add(cityText2);
        pane2.add(districtName);
        pane2.add(next2);



        mainPanel.setLayout(cards);
        mainPanel.add(pane1, "pane1");
        mainPanel.add(pane2, "pane2");
        mainPanel.add(pane3, "pane3");
        cards.show(mainPanel, "pane1");

        add(mainPanel);
        setVisible(true);
    }

    public void initializeCard3(){
        GridLayout newGrid = new GridLayout(4, 4);
        pane3.setLayout(newGrid);
        JLabel route = new JLabel("Your route:");
        pane3.add(route);
        for(Iterator iter = districtList.getIterator(); iter.hasNext();){
            String name = iter.next().name;
            JLabel temp = new JLabel(name + "->");
            pane3.add(temp);
        }
        Iterator iterator = districtList.getIterator();
        disc = iterator.next();
        JLabel districtLabel = new JLabel("District:");
        JLabel nameLabel = new JLabel("Name");
        JLabel populationLabel = new JLabel("Residents");
        JLabel profitLabel = new JLabel("Profit");

        pane3.add(districtLabel);
        pane3.add(nameLabel);
        pane3.add(populationLabel);
        pane3.add(profitLabel);

        JLabel district = new JLabel("District:");
        JLabel name = new JLabel(disc.name+ ",");
        JLabel population = new JLabel("" + disc.residents + ",");
        JLabel profit = new JLabel("" + disc.getSaleProfit());

        JButton next3 = new JButton("Next");
        next3.addActionListener(e -> {
            if(iterator.hasNext()){
                disc = iterator.next();
                name.setText(disc.name+ ",");
                population.setText("" + disc.residents + ",");
                profit.setText("" + disc.getSaleProfit());
            }
        });
        pane3.add(district);
        pane3.add(name);
        pane3.add(population);
        pane3.add(profit);
        pane3.add(next3);
    }
}
