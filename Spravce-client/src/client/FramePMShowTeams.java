package client;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.Collection;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;

public class FramePMShowTeams extends JFrame
{
  private static HashMap<String, Team> activeTeams = new HashMap();
  private static HashMap<String, Team> archivedTeams = new HashMap();

  private String id = null;
  private Font headline = new Font("Arial", 0, 30);
  private JButton[] viewButtons;
  private JButton[] editButtons;
  final Object[] tableHeaderData = { "ID", "Name", "Goal", 
    "Number of members" };

  public FramePMShowTeams(String id)
  {
    this.id = id;
    initComponents();
  }

  private void initComponents() {
    setDefaultCloseOperation(2);
    setTitle("Teams - " + this.id);
    setSize(640, 450);
    setMinimumSize(new Dimension(640, 450));

    getContentPane().setLayout(new GridLayout(2, 1));
    getContentPane().add(JSPActiveTeams());
    getContentPane().add(JSPArchivedTeams());
    setVisible(true);
  }

  public JScrollPane JSPActiveTeams() {
    JPanel panel = new JPanel();
    GridBagLayout gridbag = new GridBagLayout();
    GridBagConstraints constraint = new GridBagConstraints();
    panel.setLayout(gridbag);
    constraint.fill = 2;

    JLabel headlineActive = new JLabel("Active teams");
    headlineActive.setFont(this.headline);
    constraint.gridwidth = 5;
    constraint.gridx = 0;
    constraint.gridy = 0;
    gridbag.setConstraints(headlineActive, constraint);
    panel.add(headlineActive);

    constraint.gridwidth = 1;
    constraint.weightx = 1.0D;

    this.viewButtons = new JButton[activeTeams.values().size()];
    for (int i = 0; i < this.viewButtons.length; i++) {
      this.viewButtons[i] = new JButton("View team");
    }
    this.editButtons = new JButton[activeTeams.values().size()];
    for (int i = 0; i < this.editButtons.length; i++) {
      this.editButtons[i] = new JButton("Edit team");
    }

    int i = 1;
    for (Team team : activeTeams.values()) {
      JLabel id = new JLabel(team.getId()+"");
      JLabel name = new JLabel(team.getName());
      JLabel goal = new JLabel(team.getGoal());
      JLabel numMembers = new JLabel(team.getMembers().length+"");

      constraint.gridx = 0;
      constraint.gridy = i;
      gridbag.setConstraints(id, constraint);
      panel.add(id);

      constraint.gridx = 1;
      gridbag.setConstraints(name, constraint);
      panel.add(name);

      constraint.gridx = 2;
      gridbag.setConstraints(goal, constraint);
      panel.add(goal);

      constraint.gridx = 3;
      gridbag.setConstraints(numMembers, constraint);
      panel.add(numMembers);

      constraint.gridx = 4;
      gridbag.setConstraints(this.viewButtons[(i - 1)], constraint);
      panel.add(this.viewButtons[(i - 1)]);

      constraint.gridx = 5;
      gridbag.setConstraints(this.editButtons[(i - 1)], constraint);
      panel.add(this.editButtons[(i - 1)]);

      i++;
    }
    JScrollPane jsp = new JScrollPane(panel);
    return jsp;
  }

  public JScrollPane JSPArchivedTeams() {
    JPanel panel = new JPanel();
    GridBagLayout gridbag = new GridBagLayout();
    GridBagConstraints constraint = new GridBagConstraints();
    panel.setLayout(gridbag);
    constraint.fill = 2;

    JLabel headlineArchived = new JLabel("Archived teams");
    headlineArchived.setHorizontalAlignment(2);
    headlineArchived.setFont(this.headline);
    constraint.gridwidth = 1;
    constraint.gridx = 0;
    constraint.gridy = 0;
    gridbag.setConstraints(headlineArchived, constraint);
    panel.add(headlineArchived);

    constraint.gridwidth = 1;
    constraint.weightx = 1.0D;

    Object[][] tableData = new Object[archivedTeams.values().size()][this.tableHeaderData.length];
    int tmp = 0;
    for (Team team : archivedTeams.values()) {
      tableData[tmp][0] = Integer.valueOf(team.getId());
      tableData[tmp][1] = team.getName();
      tableData[tmp][2] = team.getGoal();
      tableData[tmp][3] = Integer.valueOf(team.getMembers().length);
      tmp++;
    }

    DefaultTableModel tableModel = new DefaultTableModel(tableData, 
      this.tableHeaderData);

    Object table = new JTable(tableModel) {
      public boolean isCellEditable(int rowIndex, int colIndex) {
        return false;
      }
    };
    ((JTable)table).getTableHeader().setReorderingAllowed(false);
    constraint.gridy = 1;
    panel.add(((JTable)table).getTableHeader(), constraint);

    ((JTable)table).setGridColor(Color.BLACK);
    ((JTable)table).setShowVerticalLines(false);

    constraint.gridy = 2;
    gridbag.setConstraints((Component)table, constraint);
    panel.add((Component)table);

    JScrollPane jsp = new JScrollPane(panel);
    return (JScrollPane)jsp;
  }

  public static void main(String[] args)
  {
    Team team1 = new Team(1);
    team1.setName("Team 1");
    team1.setGoal("Goal 1");
    team1.addMember(111);
    team1.isActive(Boolean.valueOf(true));
    Team team2 = new Team(2);
    team2.setName("Team 2");
    team2.setGoal("Goal 2");
    team2.addMember(112);
    team2.addMember(113);
    team2.addMember(114);
    team2.isActive(Boolean.valueOf(false));
    Team team3 = new Team(3);
    team3.setName("Team 3");
    team3.setGoal("Goal 3");
    team3.isActive(Boolean.valueOf(true));
    Team team4 = new Team(4);
    team4.setName("Team 4");
    team4.setGoal("Goal 4");
    team4.addMember(121);
    team4.isActive(Boolean.valueOf(false));
    team4.addMember(122);
    team4.addMember(123);
    team4.addMember(124);

    activeTeams.put("Team1", team1);
    activeTeams.put("Team2", team2);
    activeTeams.put("Team3", team3);
    activeTeams.put("Team11", team1);
    activeTeams.put("Team12", team2);
    activeTeams.put("Team13", team3);
    activeTeams.put("Team21", team1);
    activeTeams.put("Team22", team2);
    activeTeams.put("Team23", team3);

    archivedTeams.put("Team1", team1);
    archivedTeams.put("Team2", team2);
    archivedTeams.put("Team3", team3);
    archivedTeams.put("Team4", team4);
    archivedTeams.put("Team11", team1);
    archivedTeams.put("Team12", team2);
    archivedTeams.put("Team13", team3);
    archivedTeams.put("Team14", team4);
    archivedTeams.put("Team21", team1);
    archivedTeams.put("Team22", team2);
    archivedTeams.put("Team23", team3);
    archivedTeams.put("Team24", team4);

    FramePMShowTeams fst = new FramePMShowTeams("Jmeno Prijmeni");
    fst.setVisible(true);
  }
}