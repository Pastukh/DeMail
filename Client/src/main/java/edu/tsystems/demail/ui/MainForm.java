package edu.tsystems.demail.ui;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import edu.tsystems.demail.Client;
import edu.tsystems.demail.DTO.*;
import edu.tsystems.demail.MyListModel;
import edu.tsystems.demail.MyTableModel;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: Ivan Pastukh
 * Date: 22.06.13
 * Time: 0:51
 */
public class MainForm {
    JFrame frame;
    private JPanel panel1;
    private JList list1;
    private JTable table1;
    private JTextPane textPane1;
    private JButton button1;
    private JButton button2;
    private JLabel label1;
    private JButton sendButton;
    private JButton closeButton;
    private ArrayList<MailDTO> mails = new ArrayList<MailDTO>();
    private ArrayList<ArrayList<MailDTO>> allMails = new ArrayList<ArrayList<MailDTO>>();
    private ArrayList<FolderDTO> folders = new ArrayList<FolderDTO>();
    private MyTableModel tModel;
    private MyListModel lModel;
    private UserDTO user = new UserDTO();
    private MailDTO activeMailDTO = new MailDTO();
    private FolderDTO activeFolder = new FolderDTO();


    public MainForm() {
        $$$setupUI$$$();
        list1.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);

                if (e.getButton() == MouseEvent.BUTTON3) {
                    JPopupMenu menu = new JPopupMenu();
                    JMenuItem item = new JMenuItem("Delete");
                    item.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            deleteFolder();
                            folders.remove(list1.getSelectedIndex());
                            lModel = new MyListModel(folders);
                            list1.setModel(lModel);
//                            JOptionPane.showMessageDialog(panel1, "Delete "
//                                    + list1.getSelectedValue());
                        }
                    });
                    menu.add(item);
                    JMenuItem item2 = new JMenuItem("Create new");
                    item2.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            String s = (String) JOptionPane.showInputDialog(
                                    frame,
                                    "Enter folder name",
                                    "Customized Dialog",
                                    JOptionPane.PLAIN_MESSAGE,
                                    null, null, "TEST"
                            );

                            if ((s != null) && (s.length() > 0)) {
                                createFolder(s, activeFolder.getMailBoxId());
                                FolderDTO folder = new FolderDTO();
                                folder.setName(s);
                                folders.add(folder);
                                lModel = new MyListModel(folders);
                                list1.setModel(lModel);


                            }

                        }
                    });
                    menu.add(item2);
                    menu.show(list1, 40, list1.getCellBounds(
                            list1.getSelectedIndex(),
                            list1.getSelectedIndex()).y + 20);
                }
            }
        });
        list1.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {

            }
        });
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateFields();
            }
        });
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeLine(mails.size() - 1);

            }
        });
        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int row = table1.getSelectedRow();
                showLine(row);
                activeMailDTO.setId(mails.get(row).getId());

//                System.out.println(mails.get(row).getId());
            }
        });
        list1.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {

            }
        });
        list1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);    //To change body of overridden methods use File | Settings | File Templates.
                //show list item

                int index = list1.getSelectedIndex();
                if (folders.get(index).getId() == 0) return;
                showMailsInFolder(index);
                activeFolder.setId(folders.get(index).getId());
                activeFolder.setName(folders.get(index).getName());
                activeFolder.setMailBoxId(folders.get(index).getMailBoxId());
//                label1.setText(activeFolder.toString());
            }
        });
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    createFirstMail();
                } catch (IOException e1) {
                    e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
        });
        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                if (e.getButton() == MouseEvent.BUTTON3) {
                    JPopupMenu menu = new JPopupMenu();
                    JMenuItem item = new JMenuItem("Delete");
                    item.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            deleteMessage();
//                            updateFields();
                            removeLine(table1.getSelectedRow());
                        }
                    });
                    menu.add(item);
                    JMenuItem item2 = new JMenuItem("Move");
                    item2.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            Object[] lists = new Object[folders.size()];
                            int i = 0;
                            for (FolderDTO folder : folders) {
                                lists[i++] = folder.getName();
                            }

                            String s = (String) JOptionPane.showInputDialog(
                                    frame,
                                    "Select folders",
                                    "Customized Dialog",
                                    JOptionPane.PLAIN_MESSAGE,
                                    null,
                                    lists, lists[0]
                            );
                            i = 0;
                            for (Object list : lists) {
                                if (list.toString() == s) {
                                    System.out.println("index folder to move " + i);
                                    break;
                                }
                                i++;
                            }

                            if ((s != null) && (s.length() > 0)) {
                                moveMessage(s);
                                updateFields();
//                                MailDTO moveMail = new MailDTO();
//                                moveMail = mails.get(table1.getSelectedRow());
//                                removeLine(table1.getSelectedRow());
//                                mails.addAll(allMails.get(i));
//                                mails.add(moveMail);
//                                allMails.remove(i - 1);
//                                allMails.add(i - 1, mails);
                            }
                        }
                    });
                    menu.add(item2);
                    menu.show(table1, 40, 20);
//                            table1.getBounds(
//                            table1.getSelectedRow(),
//                            table1.getSelectedRow()).y);
                }
            }
        });
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new NewMessage().start(user);
            }
        });
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
    }

    private void updateFields() {
        fillListFolders();
        fillAllMails();
        list1.setSelectedIndex(0);
        showMailsInFolder(0);

    }

    private void fillListFolders() {
        try {
            folders = getListFoldersByUserId(user);
            lModel = new MyListModel(folders);
            list1.setModel(lModel);
        } catch (IOException e1) {
            e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    private ArrayList<MailDTO> fillMails(FolderDTO folder) {
        ArrayList<MailDTO> list = new ArrayList<MailDTO>();
        try {
            System.out.println(folder);
            list = getMailsByUserId(folder);
//            System.out.println(list);
            if (list == null) {
                System.out.println("folder " + folder.getName() + " empty");
                return null;
            }
//            mails.clear();
//            mails.addAll(list);
//            tModel.fireTableDataChanged();
//            System.out.println("All ok!");
        } catch (IOException e1) {
            e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return list;
    }

    private void fillAllMails() {
        ArrayList<MailDTO> mails;
        if (folders == null) return;
//        allMails = new ArrayList<ArrayList<MailDTO>>();
        allMails.clear();
        for (FolderDTO folder : folders) {
            mails = new ArrayList<MailDTO>();
            mails.addAll(fillMails(folder));
            allMails.add(mails);
        }
        System.out.println("Folders filled");
    }

    private void deleteFolder() {
        FolderDTO folderDTO = new FolderDTO();
        folderDTO.setId(activeFolder.getId());
        System.out.println("delete folder id " + folderDTO.getId());
        try {
            System.out.println("try delete folder");
            Client.sendTask("DELETE_FOLDER", folderDTO);
        } catch (IOException e) {
            e.printStackTrace();
        }
        BaseDTO baseDTO = Client.getAnswer(); //return null
    }

    private void createFolder(String name, int mailBoxId) {
        FolderDTO folderDTO = new FolderDTO();
        folderDTO.setMailBoxId(mailBoxId);
        folderDTO.setName(name);
        folderDTO.setSystem(false);
        System.out.println(folderDTO);
        try {
            System.out.println("try create folder");
            Client.sendTask("CREATE_FOLDER", folderDTO);
        } catch (IOException e) {
            e.printStackTrace();
        }
        BaseDTO baseDTO = Client.getAnswer(); //return null
    }

    private void deleteMessage() {
//        activeMailDTO.setId(1);
        System.out.println(activeMailDTO);
        try {
            System.out.println("try delete message" + activeMailDTO.getId());
            Client.sendTask("DELETE_MESSAGE", activeMailDTO);
//            moveMessage("Trash");
        } catch (IOException e) {
            e.printStackTrace();
        }
        BaseDTO baseDTO = Client.getAnswer(); //return null
    }

    private void moveMessage(String folderTo) {
//        activeMailDTO.setId(1);
        if (folderTo == null) {
            System.out.println("Wrong folder name!");
            return;
        }
        activeMailDTO.setToId(user.getId()); //hide user id
        activeMailDTO.setSubject(folderTo); //hide FolderTo
        System.out.println(activeMailDTO);
        try {
            System.out.println("try move message " + activeMailDTO);
            Client.sendTask("MOVE", activeMailDTO);
        } catch (IOException e) {
            e.printStackTrace();
        }
        BaseDTO baseDTO = Client.getAnswer(); //return null
    }


    private void showMailsInFolder(int index) {
        if (allMails == null) return;
        mails.clear();
        mails.addAll(allMails.get(index));
        System.out.println(mails);
        tModel.fireTableDataChanged();
    }

    private void showLine(int index) {
//        textPane1.setText(mails.get(0).toString());
        textPane1.setText(mails.get(index).getBody());
    }

    private void addLine(MailDTO line) {
        mails.add(line);
        tModel.fireTableDataChanged();
    }

    private ArrayList<MailDTO> getMailsByUserId(FolderDTO folderDTO) throws IOException {
        ArrayList<MailDTO> mails = new ArrayList<MailDTO>();
        List<BaseDTO> listMails = null;
        Client.sendTask("GET_MAILS", folderDTO);
        listMails = Client.getList();
        if (listMails == null) return null;
        for (BaseDTO mail : listMails) {
            System.out.println(mail);
            mails.add((MailDTO) mail);
        }
        return mails;
    }

    private void removeLine(int index) {
        mails.remove(index);
        allMails.get(list1.getSelectedIndex()).remove(index);
        tModel.fireTableDataChanged();
    }

    private ArrayList<FolderDTO> getListFoldersByUserId(UserDTO userDTO) throws IOException {
        ArrayList<FolderDTO> folders = new ArrayList<FolderDTO>();
        List<BaseDTO> listFolders;
        Client.sendTask("GET_FOLDERS", userDTO);
        listFolders = Client.getList();
        if (listFolders == null) return null;
        for (BaseDTO folder : listFolders) {
            folders.add((FolderDTO) folder);
        }
        return folders;
    }

    private void makeForm() {
        frame = new JFrame("MainForm");
        frame.setContentPane(this.panel1);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void showMailBoxName() {
        if (user != null) label1.setText(user.getLogin() + "@demail.de");
        else label1.setText("user empty");
    }

    private void createFirstMail() throws IOException {
        MailBoxDTO mailBoxDTO = new MailBoxDTO();
        System.out.println("try to create a first message for " + user);
        mailBoxDTO.setUserId(user.getId());
        mailBoxDTO.setMailbox(user.getLogin() + "demail.de");
        Client.sendTask("DO_FIRST_MESSAGE", mailBoxDTO);
        BaseDTO baseDTO = Client.getAnswer();
        if (baseDTO != null) System.out.println("First message create!");
    }

    public void start(UserDTO userDTO) {
        makeForm();
        user = userDTO;
        showMailBoxName();
        fillListFolders();
        fillAllMails();
//        fillMails();
    }

    private void createUIComponents() {
//        mails.add(new MailDTO("Myke", "John", "Very major!", new Date(), false));
//        folders.add(new FolderDTO("MY_FOLDER", true));
        lModel = new MyListModel(folders);
        list1 = new JList(lModel);


        tModel = new MyTableModel(mails);
        table1 = new JTable(tModel);
        table1.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table1.getColumnModel().getColumn(0).setPreferredWidth(80);
        table1.getColumnModel().getColumn(1).setPreferredWidth(80);
        table1.getColumnModel().getColumn(2).setPreferredWidth(120);
//        table1.getTableHeader().setResizingAllowed(false);
        table1.setDefaultRenderer(Object.class, new LastRowBold());
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        createUIComponents();
        panel1 = new JPanel();
        panel1.setLayout(new FormLayout("fill:d:noGrow,left:4dlu:noGrow,fill:105px:noGrow,fill:90px:grow,fill:max(d;150px):grow,left:4dlu:noGrow,fill:max(d;4px):noGrow", "center:max(d;4px):noGrow,top:4dlu:noGrow,top:4dlu:noGrow,top:191px:grow,top:4dlu:noGrow,fill:100px:grow,top:4dlu:noGrow,center:max(d;4px):noGrow"));
        panel1.setAutoscrolls(false);
        panel1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), null));
        final JScrollPane scrollPane1 = new JScrollPane();
        CellConstraints cc = new CellConstraints();
        panel1.add(scrollPane1, cc.xyw(1, 6, 7, CellConstraints.FILL, CellConstraints.FILL));
        textPane1 = new JTextPane();
        textPane1.setText("");
        scrollPane1.setViewportView(textPane1);
        final JScrollPane scrollPane2 = new JScrollPane();
        panel1.add(scrollPane2, cc.xyw(4, 4, 4, CellConstraints.FILL, CellConstraints.FILL));
        scrollPane2.setViewportView(table1);
        final JSeparator separator1 = new JSeparator();
        separator1.setFocusable(true);
        panel1.add(separator1, cc.xyw(1, 5, 7, CellConstraints.FILL, CellConstraints.FILL));
        button1 = new JButton();
        button1.setText("Update");
        panel1.add(button1, cc.xy(1, 8, CellConstraints.LEFT, CellConstraints.DEFAULT));
        final JScrollPane scrollPane3 = new JScrollPane();
        panel1.add(scrollPane3, cc.xyw(1, 4, 3, CellConstraints.FILL, CellConstraints.FILL));
        scrollPane3.setViewportView(list1);
        label1 = new JLabel();
        label1.setName("label1");
        label1.setText("Label");
        panel1.add(label1, cc.xyw(1, 1, 3));
        sendButton = new JButton();
        sendButton.setName("sendButton");
        sendButton.setText("sendButton");
        panel1.add(sendButton, cc.xy(3, 8, CellConstraints.LEFT, CellConstraints.DEFAULT));
        button2 = new JButton();
        button2.setText("FirstMessage");
        panel1.add(button2, cc.xy(4, 8, CellConstraints.LEFT, CellConstraints.DEFAULT));
        closeButton = new JButton();
        closeButton.setText("Close");
        panel1.add(closeButton, cc.xy(5, 8, CellConstraints.RIGHT, CellConstraints.DEFAULT));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel1;
    }

    class LastRowBold extends DefaultTableCellRenderer {
        public Component getTableCellRendererComponent(JTable table,
                                                       Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            JLabel parent = (JLabel) super.getTableCellRendererComponent(table,
                    value, isSelected, hasFocus, row, column);
//            if (column == 4 && (String) parent.getText() == "true")
            if (mails.get(row).getRead() == false)
                parent.setFont(
                        parent.getFont().deriveFont(Font.BOLD));
            return parent;
        }
    }

}
