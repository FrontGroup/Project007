package client;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class FramePMShowTeams extends JFrame {
	private static HashMap<String, Team> activeTeams = new HashMap();
	private static HashMap<String, Team> archivedTeams = new HashMap();

	private SourceUser PM = null;
	private Font headline = new Font("Arial", 0, 30);
	private JButton[] viewButtons;
	private JButton[] editButtons;
	private ActionListener[] viewListeners;
	private ActionListener[] editListeners;
	final Object[] tableHeaderData = { "ID", "Name", "Goal",
			"Number of members" };

	public FramePMShowTeams(SourceUser pm) {
		this.PM = pm;
		initComponents();
	}

	private void initComponents() {
		setDefaultCloseOperation(2);
		setTitle("Teams - " + PM.getName() + PM.getLastname());
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
		headlineActive.setFont(headline);
		constraint.gridwidth = 1;
		constraint.gridx = 0;
		constraint.gridy = 0;
		gridbag.setConstraints(headlineActive, constraint);
		panel.add(headlineActive);

		constraint.gridwidth = 1;
		constraint.weightx = 1.0D;

		viewButtons = new JButton[activeTeams.values().size()];
		viewListeners = new ActionListener[activeTeams.values().size()];
		for (int i = 0; i < viewButtons.length; i++) {
			viewButtons[i] = new JButton("View team");
			viewButtons[i].setName("btnView" + i);
			viewListeners[i] = new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					System.out.println("Button View team ID="
							+ arg0.toString().charAt(
									arg0.toString().length() - 1));
				}
			};
			viewButtons[i].addActionListener(viewListeners[i]);
		}
		editButtons = new JButton[activeTeams.values().size()];
		editListeners = new ActionListener[activeTeams.values().size()];
		for (int i = 0; i < editButtons.length; i++) {
			editButtons[i] = new JButton("Edit team");
			editButtons[i].setName("btnEdit" + i);
			editListeners[i] = new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					System.out.println("Button Edit team ID="
							+ arg0.toString().charAt(
									arg0.toString().length() - 1));
				}
			};
			editButtons[i].addActionListener(editListeners[i]);
		}

		int i = 1;
		if (activeTeams.values().isEmpty()) {
			headlineActive.setText("Active teams - none");
			constraint.gridx = 0;
			constraint.gridy = 1;
			JLabel tmpLBL = new JLabel(" ");
			gridbag.setConstraints(tmpLBL, constraint);
			panel.add(tmpLBL);
		} else {
			for (Team team : activeTeams.values()) {
				JLabel id = new JLabel(team.getId() + "");
				JLabel name = new JLabel(team.getName());
				JLabel goal = new JLabel(team.getGoal());
				JLabel numMembers = new JLabel(team.getMembers().size() + "");

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
				gridbag.setConstraints(viewButtons[(i - 1)], constraint);
				panel.add(viewButtons[(i - 1)]);

				constraint.gridx = 5;
				gridbag.setConstraints(editButtons[(i - 1)], constraint);
				panel.add(editButtons[(i - 1)]);

				i++;
			}
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
		headlineArchived.setFont(headline);
		constraint.gridwidth = 1;
		constraint.gridx = 0;
		constraint.gridy = 0;
		gridbag.setConstraints(headlineArchived, constraint);
		panel.add(headlineArchived);

		constraint.gridwidth = 1;
		constraint.weightx = 1.0D;

		Object[][] tableData = new Object[archivedTeams.values().size()][tableHeaderData.length];
		int tmp = 0;
		if (archivedTeams.values().isEmpty()) {
			headlineArchived.setText("Archived teams - none");
		} else {
			for (Team team : archivedTeams.values()) {
				tableData[tmp][0] = Integer.valueOf(team.getId());
				tableData[tmp][1] = team.getName();
				tableData[tmp][2] = team.getGoal();
				tableData[tmp][3] = Integer.valueOf(team.getMembers().size());
				tmp++;
			}
		}

		DefaultTableModel tableModel = new DefaultTableModel(tableData,
				tableHeaderData);

		final JTable table = new JTable(tableModel) {
			public boolean isCellEditable(int rowIndex, int colIndex) {
				return false;
			}
		};
		table.getTableHeader().setReorderingAllowed(false);
		constraint.gridy = 1;
		if (!archivedTeams.values().isEmpty()) {
			panel.add(((JTable) table).getTableHeader(), constraint);
		}

		table.setGridColor(Color.BLACK);
		table.setShowVerticalLines(false);

		constraint.gridy = 2;
		gridbag.setConstraints((Component) table, constraint);
		panel.add((Component) table);

		table.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {

					@Override
					public void valueChanged(ListSelectionEvent e) {
						// TODO Auto-generated method stub
						if (e.getValueIsAdjusting()) {
							System.out.println("Selected row: "
									+ table.getSelectedRow());
						}
					}
				});

		JScrollPane jsp = new JScrollPane(panel);
		return (JScrollPane) jsp;
	}

	public static void main(String[] args) {
		// testing data
		/*Team team1 = new Team(1);
		team1.setName("Team 1");
		team1.setGoal("Goal 1");
		team1.addMember(new User(111, 1));
		team1.isActive(true);
		Team team2 = new Team(2);
		team2.setName("Team 2");
		team2.setGoal("Goal 2");
		team2.addMember(new User(112, 1));
		team2.addMember(new User(113, 1));
		team2.addMember(new User(114, 1));
		team2.isActive(false);
		Team team3 = new Team(3);
		team3.setName("Team 3");
		team3.setGoal("Goal 3");
		team3.isActive(true);
		Team team4 = new Team(4);
		team4.setName("Team 4");
		team4.setGoal("Goal 4");
		team4.addMember(new User(121, 1));
		team4.isActive(false);
		team4.addMember(new User(122, 1));
		team4.addMember(new User(123, 1));
		team4.addMember(new User(124, 1));

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
		archivedTeams.put("Team12", team2);
		archivedTeams.put("Team11", team1);
		archivedTeams.put("Team13", team3);
		archivedTeams.put("Team14", team4);
		archivedTeams.put("Team21", team1);
		archivedTeams.put("Team22", team2);
		archivedTeams.put("Team23", team3);
		archivedTeams.put("Team24", team4);*/

		SourceUser u = new SourceUser();
		u.loadData(12);
		FramePMShowTeams fst = new FramePMShowTeams(u);
		fst.setVisible(true);
	}
}