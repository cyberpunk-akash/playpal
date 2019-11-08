package playpal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.*;
import static playpal.dashboard.userid;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author MAHE
 */
public class ViewTournament extends javax.swing.JFrame{

    /**
     * Creates new form ViewTournament
     */Float balance,entryFee,balance1;
     int tour_owner;
     
    
    public ViewTournament() {
        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;
        int count=0;
        
        String user = "root";
        String pass = "kent";
        
        ActionListener listener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() instanceof JButton){
                String text = ((JButton) e.getSource()).getText();
                
            //JOptionPane.showMessageDialog(null, text.substring(5));
            int result = JOptionPane.showConfirmDialog(null,"Are you sure that you want to participate in "
                    +text.substring(5)+"? The price amount will be deducted from your wallet", "",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            
            if(result == JOptionPane.YES_OPTION){
               //System.out.print("Yippe");
               
          try 
           {
                Connection myConn = null;
                Statement myStmt = null;
                ResultSet myRs = null;
                ResultSet myRs1 = null;
                ResultSet myRs2 = null;
                int tour_id=0;
                String tour_name=text.substring(5);
                String user = "root";
                String pass = "kent";
            myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/playpal_db", user, pass);
            myStmt = myConn.createStatement();
             String query1="SELECT balance from user where user_id='"+userid+"'";
            myRs=myStmt.executeQuery(query1);
            while(myRs.next())
            {
                balance=Float.parseFloat(myRs.getString("balance"));
                //JOptionPane.showMessageDialog(null,"Congrats, your participation is confirmed!");
            }
            
            
            String query2="SELECT tour_id,entryFee,tour_owner from tournament where tour_name='"+tour_name+"'";
            myRs2=myStmt.executeQuery(query2);
            while(myRs2.next())
            {
                tour_id=Integer.parseInt(myRs2.getString("tour_id"));
                entryFee=Float.parseFloat(myRs2.getString("entryFee"));
                tour_owner =Integer.parseInt(myRs2.getString("tour_owner"));
                
            }
             if(balance-entryFee<0)
            {
                JOptionPane.showMessageDialog(null,"We're sorry. You don't have enough money in wallet");
            }
                else{
            myStmt.executeUpdate("INSERT INTO tourn_participation(tour_id,user_id) VALUES("
                        +tour_id+","+userid+")");
            myStmt.executeUpdate("update user set balance="+(balance-entryFee)+"where user_id="+userid);
            String query3="SELECT balance from user where user_id="+tour_owner;
            myRs=myStmt.executeQuery(query3);
            while(myRs.next())
            {
                balance1=Float.parseFloat(myRs.getString("balance"));
                //JOptionPane.showMessageDialog(null,"Congrats, your participation is confirmed!");
            }
            
            myStmt.executeUpdate("update user set balance="+(balance1+entryFee)+"where user_id="+tour_owner);
           JOptionPane.showMessageDialog(null,"Congrats, your participation is confirmed!");
           
             }
        }
          catch (Exception exc){
            exc.printStackTrace();
        }
               
               new ViewTournament().setVisible(true);
               dispose();
               
            }
        }
        }
    };
        initComponents();
        String query;
        
        try 
           {
            ResultSet myRs1 = null;
            myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/playpal_db", user, pass);
            myStmt = myConn.createStatement();
            query="SELECT count(tour_id) as c from tournament";
            myRs1=myStmt.executeQuery(query);
            while(myRs1.next())
            {
                count=Integer.parseInt(myRs1.getString("c"));
                
            }
            
            JTextArea tourn[]= new JTextArea[count];
            JButton participate[]= new JButton[count];
            query="select * from tournament where tour_id not in "
                    + "(select tour_id from tourn_participation where user_id="+userid+");";
            myRs1=myStmt.executeQuery(query);
            int i=0;
            while(myRs1.next())
            {
               String tour_name=myRs1.getString("tour_name");
               tourn[i]= new JTextArea();
               participate[i]=new JButton("JOIN "+tour_name);
               tourn[i].setBounds(60,80+i*170,300,150);
               participate[i].setBounds(400,130+i*170,150,50);
               participate[i].addActionListener(listener);
               this.add(tourn[i]);
               this.add(participate[i]);
               tourn[i].setText(tour_name+ "  ----> "+myRs1.getString("tour_sport")
                + " Tournament\n\nEntry Fee : Rs."+ myRs1.getString("entryFee")+ "\nVenue: "+myRs1.getString("tour_venue")+
                        "\nSchedule: "+myRs1.getString("tour_date")+"\nTimings: "+myRs1.getString("tour_time"));
                tourn[i].setEditable(false);
                i++;
            }
        
            //dispose();
        }
          catch (Exception exc){
            exc.printStackTrace();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 0, 0));

        jLabel1.setFont(new java.awt.Font("Microsoft JhengHei", 0, 36)); // NOI18N
        jLabel1.setText("TOURNAMENTS FEED");

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/playpal/wilson.jpeg"))); // NOI18N
        jLabel2.setText("jLabel2");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 464, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 698, Short.MAX_VALUE))
        );

        jButton3.setText("DASHBOARD");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(139, 139, 139)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 307, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton3)
                        .addGap(59, 59, 59)))
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jButton3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        new dashboard(userid).setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ViewTournament.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewTournament.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewTournament.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewTournament.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewTournament().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables

}
