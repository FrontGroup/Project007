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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class FramePMShowTeams extends JFrame {
	private static HashMap<Integer, Team> activeTeams = new HashMap();
	private static HashMap<Integer, Team> archivedTeams = new HashMap();

	private User PM;
	private Font headline = new Font("Arial", 0, 30);
	private JButton[] viewButtons;
	private JButton[] editButtons;
	private ActionListener[] viewListeners;
	private ActionListener[] editListeners;
	final Object[] tableHeaderData = { "ID", "Name", "Goal",
			"Number of members" };

	public FramePMShowTeams(int id) {
		SourceUser su = new SourceUser();
		
		String ld = su.loadData(id);
		
		if (ld.startsWith("KO")) {
			JOptionPane.showMessageDialog(null, ld.substring(3),
					"Error in loading", JOptionPane.ERROR_MESSAGE);
		}
		SourceTeam st = new SourceTeam();
		
		String ldfpm = st.loadDataFromPM(id);
		
		if (ldfpm.startsWith("KO")){
			JOptionPane.showMessageDialog(null, ldfpm.substring(3),
					"Error in loading", JOptionPane.ERROR_MESSAGE);
		}

		this.PM = su.getUser(id);
		for (Team t : st.getAllTeams().values()) {
			if (t.isActive()) {
				activeTeams.put(t.getId(), t);
			} else {
				archivedTeams.put(t.getId(), t);
			}
		}

		initComponents();
	}

	private void initComponents() {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("Teams - " + PM.getFullName());
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
		int tmpV = 0;
		for (Team tV : activeTeams.values()) {
			viewButtons[tmpV] = new JButton("View team");
			viewButtons[tmpV].setName("btnView" + tV.getId());
			viewListeners[tmpV] = new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					int a = 1;
					String idV = "";
					while (arg0.toString().charAt(arg0.toString().length() - a) != 'w') {
						idV += arg0.toString().charAt(
								arg0.toString().length() - a);
						a++;
					}
					new FramePMEditTeam(PM.getId(), Integer.parseInt(idV),
							false).setVisible(true);
				}
			};
			viewButtons[tmpV].addActionListener(viewListeners[tmpV]);
			tmpV++;
		}
		editButtons = new JButton[activeTeams.values().size()];
		editListeners = new ActionListener[activeTeams.values().size()];
		int tmpE = 0;
		for (Team tE : activeTeams.values()) {
			editButtons[tmpE] = new JButton("Edit team");
			editButtons[tmpE].setName("btnEdit" + tE.getId());
			editListeners[tmpE] = new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					int b = 1;
					String idE = "";
					while (arg0.toString().charAt(arg0.toString().length() - b) != 't') {
						idE += arg0.toString().charAt(
								arg0.toString().length() - b);
						b++;
					}
					new FramePMEditTeam(PM.getId(), Integer.parseInt(idE), true)
							.setVisible(true);
				}
			};
			editButtons[tmpE].addActionListener(editListeners[tmpE]);
			tmpE++;
		}

		//writing teams into panel
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
				tableData[tmp][0] = team.getId();
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
						if (e.getValueIsAdjusting()) {
							Object o = table.getValueAt(
									table.getSelectedRow(), 0);
							int id = Integer.valueOf(o.toString());
							new FramePMEditTeam(PM.getId(), id, false).setVisible(true);
						}
					}
				});

		JScrollPane jsp = new JScrollPane(panel);
		return (JScrollPane) jsp;
	}
}