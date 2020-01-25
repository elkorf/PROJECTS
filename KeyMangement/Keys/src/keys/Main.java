package keys;
/**
 *
 * @author elkorf
 */
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

class CustomTable extends DefaultTableModel{
    public CustomTable(String abc[],int a){
        super(abc,a);
    }
    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}

public class Main extends javax.swing.JFrame{
    Connection con;
    PreparedStatement st;
    Statement st1;
    ResultSet rs;
    int i=10;
    public Main() {
        initComponents();
        try {
            con = DbConn.connect();
            st1=con.createStatement();
            rs=st1.executeQuery("select * from ISSUED_KEYS");
            int count=loadData(rs);
            int total=50;
            total_keys.setText(String.valueOf(total));
            issued_keys.setText(String.valueOf(count-1));
         }catch (Exception ex) {
            System.out.println("Exception :"+ex);
         }
        keyid.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char keyChar = e.getKeyChar();
                if (Character.isLowerCase(keyChar)) {
                    e.setKeyChar(Character.toUpperCase(keyChar));
                }
            }
        });
        issuerid.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char keyChar = e.getKeyChar();
                if (Character.isLowerCase(keyChar)) {
                    e.setKeyChar(Character.toUpperCase(keyChar));
                }
            }
        });
    }

    int loadData(ResultSet rs){
        CustomTable model = new CustomTable(new String[]{"Sr. No.", "Key ID", "Key Name","Issuer ID", "Issuer Name","Issuer Mobile", "Issue Time"}, 0);       
        int z=1;
        try{
            while(rs.next()){
                String KeyId = rs.getString("key_id");
                String KeyName = rs.getString("key_name");
                String IssuerId = rs.getString("issuer_id");
                String IssuerName = rs.getString("issuer_name");
                String IssuerMobile = rs.getString("issuer_mobile");
                String IssueTime = rs.getString("issue_time");
                model.addRow(new Object[]{z++,KeyId,KeyName, IssuerId,IssuerName,IssuerMobile,IssueTime});
            }
            entrytable.setModel(model);
        }catch(SQLException ex){
            System.out.println("Exception -00: "+ex);
        }    
        return z;
    }
    
    boolean dataValidate(String key_id,String issuer_id){
        UIManager.put("OptionPane.minimumSize",new Dimension(400,150));
        JLabel msg = new JLabel();
        msg.setFont(new Font("Times New Roman", Font.BOLD, 24)); 
        
        if(key_id.trim().length()==0 || issuer_id.trim().length()==0){
            msg.setText("Key ID or Issuer ID Connot Empty.");
            JOptionPane.showMessageDialog(this,msg, "Info" ,JOptionPane.INFORMATION_MESSAGE);
           return false;
        }else{
            try {
                rs=st1.executeQuery("SELECT * FROM keys WHERE key_id='"+key_id+"'");
                if(!rs.next()){
                    msg.setText("Invalid Key ID.");
                    JOptionPane.showMessageDialog(this,msg, "Error" ,JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            } catch (SQLException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return true;
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Insert_panel = new javax.swing.JPanel();
        title = new javax.swing.JLabel();
        issuerid = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        keyid = new javax.swing.JTextField();
        Submit = new javax.swing.JButton();
        new_key = new javax.swing.JButton();
        logo_panel = new javax.swing.JPanel();
        statistics = new javax.swing.JPanel();
        statistics_label = new javax.swing.JLabel();
        issued_keys = new javax.swing.JLabel();
        separator = new javax.swing.JSeparator();
        total_keys = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        total = new javax.swing.JLabel();
        issued = new javax.swing.JLabel();
        table_panel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        entrytable = new javax.swing.JTable();
        search = new javax.swing.JTextField();
        search_label = new javax.swing.JLabel();
        clear_search = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Insert_panel.setBackground(new java.awt.Color(204, 204, 255));

        title.setFont(new java.awt.Font("Times New Roman", 1, 55)); // NOI18N
        title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title.setText("Key Management");
        title.setName(""); // NOI18N

        issuerid.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        issuerid.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        issuerid.setToolTipText("");
        issuerid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                issueridActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Issuer ID : ");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Key ID : ");

        keyid.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        keyid.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        keyid.setToolTipText("");
        keyid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                keyidActionPerformed(evt);
            }
        });
        keyid.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                keyidKeyPressed(evt);
            }
        });

        Submit.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        Submit.setText("Submit");
        Submit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SubmitActionPerformed(evt);
            }
        });
        Submit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SubmitKeyPressed(evt);
            }
        });

        new_key.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        new_key.setText("Add New");
        new_key.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                new_keyActionPerformed(evt);
            }
        });
        new_key.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                new_keyKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout Insert_panelLayout = new javax.swing.GroupLayout(Insert_panel);
        Insert_panel.setLayout(Insert_panelLayout);
        Insert_panelLayout.setHorizontalGroup(
            Insert_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(title, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(Insert_panelLayout.createSequentialGroup()
                .addGap(104, 104, 104)
                .addGroup(Insert_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(Insert_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(issuerid, javax.swing.GroupLayout.DEFAULT_SIZE, 382, Short.MAX_VALUE)
                    .addComponent(keyid))
                .addGap(27, 27, 27)
                .addGroup(Insert_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Submit, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
                    .addComponent(new_key, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );
        Insert_panelLayout.setVerticalGroup(
            Insert_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Insert_panelLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(title)
                .addGap(21, 21, 21)
                .addGroup(Insert_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(keyid, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(new_key, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(Insert_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(issuerid, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Submit, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41))
        );

        logo_panel.setBackground(new java.awt.Color(51, 204, 255));

        javax.swing.GroupLayout logo_panelLayout = new javax.swing.GroupLayout(logo_panel);
        logo_panel.setLayout(logo_panelLayout);
        logo_panelLayout.setHorizontalGroup(
            logo_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 371, Short.MAX_VALUE)
        );
        logo_panelLayout.setVerticalGroup(
            logo_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        statistics.setBackground(new java.awt.Color(255, 204, 204));

        statistics_label.setFont(new java.awt.Font("Times New Roman", 1, 48)); // NOI18N
        statistics_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        statistics_label.setText("Statistics");

        issued_keys.setFont(new java.awt.Font("Times New Roman", 1, 170)); // NOI18N
        issued_keys.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        issued_keys.setText("0");

        separator.setForeground(new java.awt.Color(0, 0, 0));

        total_keys.setFont(new java.awt.Font("Times New Roman", 1, 170)); // NOI18N
        total_keys.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        total_keys.setText("50");

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));

        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));

        total.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        total.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        total.setText("Total Keys");

        issued.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        issued.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        issued.setText("Issued Keys");

        javax.swing.GroupLayout statisticsLayout = new javax.swing.GroupLayout(statistics);
        statistics.setLayout(statisticsLayout);
        statisticsLayout.setHorizontalGroup(
            statisticsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(statistics_label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(separator)
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jSeparator2)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, statisticsLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(statisticsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(total_keys, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(total, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(statisticsLayout.createSequentialGroup()
                .addGroup(statisticsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(issued, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(issued_keys, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 371, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        statisticsLayout.setVerticalGroup(
            statisticsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statisticsLayout.createSequentialGroup()
                .addComponent(statistics_label, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(separator, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(issued_keys, javax.swing.GroupLayout.PREFERRED_SIZE, 141, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(issued)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(total_keys, javax.swing.GroupLayout.PREFERRED_SIZE, 159, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(total)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        entrytable.setAutoCreateRowSorter(true);
        entrytable.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        entrytable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Sr. No.", "Key ID", "Key Name", "Issuer ID", "Issuer Name", "Issuer Mobile", "Issue Time"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        entrytable.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        entrytable.setRowHeight(40);
        entrytable.setSelectionBackground(new java.awt.Color(204, 204, 255));
        entrytable.getTableHeader().setResizingAllowed(false);
        entrytable.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(entrytable);
        if (entrytable.getColumnModel().getColumnCount() > 0) {
            entrytable.getColumnModel().getColumn(0).setResizable(false);
            entrytable.getColumnModel().getColumn(0).setPreferredWidth(15);
            entrytable.getColumnModel().getColumn(1).setResizable(false);
            entrytable.getColumnModel().getColumn(2).setResizable(false);
            entrytable.getColumnModel().getColumn(3).setResizable(false);
            entrytable.getColumnModel().getColumn(4).setResizable(false);
            entrytable.getColumnModel().getColumn(5).setResizable(false);
            entrytable.getColumnModel().getColumn(6).setResizable(false);
        }

        search.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        search.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        search.setToolTipText("");
        search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchActionPerformed(evt);
            }
        });
        search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                searchKeyTyped(evt);
            }
        });

        search_label.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        search_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        search_label.setText("Search : ");

        clear_search.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        clear_search.setText("Clear");
        clear_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clear_searchActionPerformed(evt);
            }
        });
        clear_search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                clear_searchKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout table_panelLayout = new javax.swing.GroupLayout(table_panel);
        table_panel.setLayout(table_panelLayout);
        table_panelLayout.setHorizontalGroup(
            table_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(table_panelLayout.createSequentialGroup()
                .addGap(141, 141, 141)
                .addComponent(search_label)
                .addGap(27, 27, 27)
                .addComponent(search)
                .addGap(27, 27, 27)
                .addComponent(clear_search, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, table_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 883, Short.MAX_VALUE))
        );
        table_panelLayout.setVerticalGroup(
            table_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, table_panelLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(table_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(search_label)
                    .addComponent(clear_search, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 416, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(table_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(Insert_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(logo_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(statistics, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(logo_panel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Insert_panel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(table_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(statistics, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(14, 14, 14))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchActionPerformed

    private void issueridActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_issueridActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_issueridActionPerformed

    private void keyidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_keyidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_keyidActionPerformed

    private void SubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SubmitActionPerformed
        // TODO add your handling code here:
        
        try {
            String key_id = null,issuer_id=null;
            issuer_id=issuerid.getText();
            key_id = keyid.getText();
            
            if(dataValidate(key_id,issuer_id)){

                String date = java.time.LocalDate.now().toString();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm", Locale.US);
                String time = formatter.format(java.time.LocalTime.now());
                st = con.prepareStatement("insert into ISSUED_KEYS (KEY_ID,KEY_NAME,ISSUER_ID,ISSUER_NAME,ISSUER_MOBILE,ISSUE_TIME) values(?,?,?,?,?,? )");
            
                if(st1.executeUpdate("delete from ISSUED_KEYS where key_id = '"+key_id+"'")>0){
                    System.out.println("Record Deleted.");
                }else{
                    st.setString(1,key_id);
                    st.setString(2,"gym'"+(i++)+"'");
                    st.setString(3,issuer_id);
                    st.setString(4,"VFBDF");
                    st.setString(5,"VFD");
                    st.setString(6,time+" "+date);
                    if(st.executeUpdate()>0){
                        System.out.println("Record inserted");
                    }
                }
            }
            issuerid.setText(null);
            keyid.setText(null);
            
            String sql="SELECT * FROM ISSUED_KEYS"; 
            rs = st1.executeQuery(sql);
            int count=loadData(rs);
            int total=50;
            total_keys.setText(String.valueOf(total));
            issued_keys.setText(String.valueOf(count-1));
        } catch (SQLException ex) {
            System.out.println("Exception : "+ex);
        }
    }//GEN-LAST:event_SubmitActionPerformed

    private void SubmitKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SubmitKeyPressed
    }//GEN-LAST:event_SubmitKeyPressed

    private void searchKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchKeyTyped
        // TODO add your handling code here:
        String serch = search.getText();
        try{  
            String sql="SELECT * FROM ISSUED_KEYS where key_name like '%"+serch+"%' or key_id like '%"+serch+"%' or issuer_name like '%"+serch+"%'";
            rs = st1.executeQuery(sql);
            loadData(rs);
        }catch(SQLException ex){
            System.out.println(ex);
        } catch (Exception ex) {
            System.out.println("Exception : "+ex);
        }    
    }//GEN-LAST:event_searchKeyTyped

    private void keyidKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_keyidKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_keyidKeyPressed

    private void new_keyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_new_keyActionPerformed
        // TODO add your handling code here:
        String key_id = keyid.getText();
        UIManager.put("OptionPane.minimumSize",new Dimension(400,150));
        JLabel msg=new JLabel();
        msg.setFont(new Font("Times New Roman", Font.BOLD, 24));
        if(key_id.trim().length()==0){
            msg.setText("Please, Enter the Key ID ");
            JOptionPane.showMessageDialog(this,msg, "Info" ,JOptionPane.INFORMATION_MESSAGE);
        }else{
            try {
                String sql;
                String key_name = "GYM";
                sql="INSERT INTO keys(key_id,key_name) VALUES('"+key_id+"','"+key_name+"')";
                if(st1.executeUpdate(sql)>0){
                    msg.setText("NEW KEY IS ADDED.");
                    JOptionPane.showMessageDialog(this,msg, "Info" ,JOptionPane.INFORMATION_MESSAGE);
                }    
            } catch (SQLException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
            keyid.setText(null);
            issuerid.setText(null);
        }
    }//GEN-LAST:event_new_keyActionPerformed

    private void new_keyKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_new_keyKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_new_keyKeyPressed

    private void clear_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clear_searchActionPerformed
        // TODO add your handling code here:
         search.setText(null);
        try {
            rs=st1.executeQuery("select * from ISSUED_KEYS");
            loadData(rs);
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }//GEN-LAST:event_clear_searchActionPerformed

    private void clear_searchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_clear_searchKeyPressed
       
    }//GEN-LAST:event_clear_searchKeyPressed
    
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
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Insert_panel;
    private javax.swing.JButton Submit;
    private javax.swing.JButton clear_search;
    private javax.swing.JTable entrytable;
    private javax.swing.JLabel issued;
    private javax.swing.JLabel issued_keys;
    private javax.swing.JTextField issuerid;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextField keyid;
    private javax.swing.JPanel logo_panel;
    private javax.swing.JButton new_key;
    private javax.swing.JTextField search;
    private javax.swing.JLabel search_label;
    private javax.swing.JSeparator separator;
    private javax.swing.JPanel statistics;
    private javax.swing.JLabel statistics_label;
    private javax.swing.JPanel table_panel;
    private javax.swing.JLabel title;
    private javax.swing.JLabel total;
    private javax.swing.JLabel total_keys;
    // End of variables declaration//GEN-END:variables
}
