package client;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Random;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class FramePMEditTeam extends JFrame {

	private boolean editing, newTeam;

	private User PM;
	private Team team;
	private static HashMap<Integer, User> members = new HashMap();

	private JLabel teamLeader = new JLabel("");
	private JTextField JTFTeamName = new JTextField();
	private JTextField JTFTeamGoal = new JTextField();
	private JTextField JTFProject = new JTextField("optional");
	private JTextField JTFTeamInfo = new JTextField("optional");

	private static DefaultListModel listModel = new DefaultListModel();
	private static JList JLMembers = new JList();

	public FramePMEditTeam(int id) {
		this.PM = loadUser(id);
		teamLeader.setText(PM.getFullName());
		this.editing = true;
		this.newTeam = true;
		team = new Team(PM.getId());
		initComponents();
	}

	public FramePMEditTeam(int userId, int teamId, boolean editing) {
		this.PM = loadUser(userId);
		teamLeader.setText(PM.getFullName());
		/*
		 * if (teamId == ) { JOptionPane.showMessageDialog(null,
		 * "Error in concstructor FramePMEditTeam", "Error",
		 * JOptionPane.ERROR_MESSAGE); } else {
		 */
		this.editing = editing;
		this.team = loadTeam(teamId);
		// }
		initComponents();
		fillInForm();
		takeMembers();
	}

	private User loadUser(int id) {
		SourceUser su = new SourceUser();
		su.loadData();
		return su.getUser(id);
	}

	private Team loadTeam(int id) {
		SourceTeam st = new SourceTeam();
		st.loadData();
		Team t = st.getTeam(id);
		return t;
	}

	private void takeMembers() {
		for (User member : team.getMembers().values()) {
			members.put(member.getId(), member);
		}
		/*
		 * for (int i = 0; i < team.getMembers().length; i++) {
		 * members.put(team.getMembers()[i].getId(), team.getMembers()[i]); }
		 */
	}

	private void initComponents() {
		setDefaultCloseOperation(2);
		setSize(450, 500);
		setMinimumSize(new Dimension(400, 500));

		getContentPane().setLayout(new GridLayout(3, 1));
		getContentPane().add(JPTeamInformation());
		getContentPane().add(JSPFoundedMembers());
		getContentPane().add(JPButtons());

	}

	private void fillInForm() {
		JTFTeamName.setText(team.getName());
		teamLeader.setText(loadUser(team.getPm()).getFullName());
		JTFTeamGoal.setText(team.getGoal());
		JTFProject.setText(team.getProject());
		JTFTeamInfo.setText(team.getProject());
		listModel.removeAllElements();
		for (User member : team.getMembers().values()) {
			addMember(member);
		}
	}

	public JPanel JPTeamInformation() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(7, 2));

		JLabel JLTeamName = new JLabel("Team name");
		JLabel JLTeamLeader = new JLabel("Leader");
		JLabel JLTeamGoal = new JLabel("Goal");
		JLabel JLProject = new JLabel("Project");
		JLabel JLTeamInfo = new JLabel("Info");
		JLabel JLMembers = new JLabel("Members");

		panel.add(JLTeamName);
		panel.add(JTFTeamName);
		panel.add(JLTeamLeader);
		panel.add(teamLeader);
		panel.add(JLTeamGoal);
		panel.add(JTFTeamGoal);
		panel.add(JLProject);
		panel.add(JTFProject);
		panel.add(JLTeamInfo);
		panel.add(JTFTeamInfo);
		panel.add(new JLabel(" "));
		panel.add(new JLabel(" "));
		panel.add(JLMembers);

		return panel;
	}

	public JScrollPane JSPFoundedMembers() {
		JPanel panel = new JPanel();
		BorderLayout layout = new BorderLayout();
		panel.setLayout(layout);

		JLMembers.setModel(listModel);

		panel.add(JLMembers, BorderLayout.NORTH);

		JScrollPane jsp = new JScrollPane(panel);
		return jsp;
	}

	public JPanel JPButtons() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(6, 2));

		JButton JBAddMembers = new JButton("Add members");
		JBAddMembers.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Need displaying window for looking users
			}
		});
		JButton JBRmMembers = new JButton("Remove selected members");
		JBRmMembers.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				removeMember(JLMembers.getSelectedIndices());
			}
		});
		JButton JBShowDetail = new JButton("Show member's detail");
		JBShowDetail.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (JLMembers.getSelectedIndex() == -1) {
					JOptionPane.showMessageDialog(null, "No one was selected!",
							"Error", JOptionPane.ERROR_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null,
							"Not implemented yet. Selected member #"
									+ JLMembers.getSelectedIndex(),
							"IMPLEMENTATION MISSING",
							JOptionPane.INFORMATION_MESSAGE);
				}
				// TODO Need displaying user's profile
			}
		});
		JButton JBSendInvitation = new JButton("Send invitation to all members");
		JBSendInvitation.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Need consultation
				JOptionPane.showMessageDialog(null,
						"Not implemented yet. Nice to have feature.",
						"IMPLEMENTATION MISSING",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
		JButton JBSaveChanges = new JButton("Save changes");
		JBSaveChanges.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (JTFTeamName.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Team name is empty!",
							"Error", JOptionPane.ERROR_MESSAGE);
				} else if (JTFTeamGoal.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Team goal is empty!",
							"Error", JOptionPane.ERROR_MESSAGE);
				} else {
					team.setName(JTFTeamName.getText());
					team.setGoal(JTFTeamGoal.getText());
					if (JTFProject.getText().equals("")
							|| JTFProject.getText().equals("optional")) {
					} else {
						team.setProject(JTFProject.getText());
					}
					if (JTFTeamInfo.getText().isEmpty()
							|| JTFTeamInfo.getText().equals("optional")) {
					} else {
						team.setInfo(JTFProject.getText());
					}
					team.removeAllMembers();
					for (User member : members.values()) {
						team.addMember(member);
					}
					// TODO Need working database
					JOptionPane.showMessageDialog(null, "Changes saved.", "OK",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		JButton JBDiscardChanges = new JButton("Discard changes");
		JBDiscardChanges.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				takeMembers();
				fillInForm();
			}
		});
		JButton JBDeleteTeam = new JButton("Delete team");
		JBDeleteTeam.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Need working database
				JOptionPane.showMessageDialog(null, "Not implemented yet.",
						"IMPLEMENTATION MISSING",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
		JButton JBArchiveTeam = new JButton("Archive team");
		JBArchiveTeam.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Need working database
				JOptionPane.showMessageDialog(null, "Not implemented yet.",
						"IMPLEMENTATION MISSING",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});

		if (editing) {
			setTitle("Editing team " + team.getName() + " (" + PM.getFullName()
					+ ")");
			panel.add(JBAddMembers);
			panel.add(JBRmMembers);
			panel.add(JBShowDetail);
			panel.add(JBSendInvitation);
			panel.add(new JLabel(" "));
			panel.add(new JLabel(" "));
			panel.add(JBSaveChanges);
			if (newTeam) {
				setTitle("New team (" + PM.getFullName() + ")");
				panel.add(new JLabel(" "));
				panel.add(new JLabel(" "));
				panel.add(new JLabel(" "));
				panel.add(new JLabel(" "));
				panel.add(new JLabel(" "));
			} else {
				panel.add(JBDiscardChanges);
				panel.add(new JLabel(" "));
				panel.add(new JLabel(" "));
				panel.add(JBDeleteTeam);
				panel.add(JBArchiveTeam);
				if (!team.isActive()) {
					JTFTeamName.setEditable(false);
					JTFTeamGoal.setEditable(false);
					JTFProject.setEditable(false);
					JTFTeamInfo.setEditable(false);
					JBAddMembers.setEnabled(false);
					JBRmMembers.setEnabled(false);
					JBSendInvitation.setEnabled(false);
					JBSaveChanges.setEnabled(false);
					JBDiscardChanges.setEnabled(false);
					JBArchiveTeam.setEnabled(false);
				}
			}
		} else {
			setTitle("Showing team " + team.getName() + " (" + PM.getFullName()
					+ ")");
			JTFTeamName.setEditable(false);
			JTFTeamGoal.setEditable(false);
			JTFProject.setEditable(false);
			JTFTeamInfo.setEditable(false);
			JBAddMembers.setEnabled(false);
			JBRmMembers.setEnabled(false);
			JBSendInvitation.setEnabled(false);
			JBSaveChanges.setEnabled(false);
			JBDiscardChanges.setEnabled(false);
			JBDeleteTeam.setEnabled(false);
			JBArchiveTeam.setEnabled(false);
			if (!team.isActive()) {
				setTitle("Showing archived team " + team.getName() + " ("
						+ PM.getFullName() + ")");
			}
			panel.add(JBAddMembers);
			panel.add(JBRmMembers);
			panel.add(JBShowDetail);
			panel.add(JBSendInvitation);
			panel.add(new JLabel(" "));
			panel.add(new JLabel(" "));
			panel.add(JBSaveChanges);
			panel.add(JBDiscardChanges);
			panel.add(new JLabel(" "));
			panel.add(new JLabel(" "));
			panel.add(JBDeleteTeam);
			panel.add(JBArchiveTeam);
		}

		return panel;
	}

	private void addMember(User member) {
		listModel.addElement(member.getId() + ", " + member.getFullName()
				+ ", " + member.getProfession());
		members.put(member.getId(), member);
	}

	private void removeMember(int[] row) {
		for (int i = 0; i < row.length; i++) {
			members.remove(whatIsSelectedId(row[i]));
			listModel.removeElementAt(row[i]);
			for (int j = i; j < row.length; j++) {
				row[j]--;
			}
		}
	}

	private int whatIsSelectedId(int row) {
		String tmp = listModel.getElementAt(row).toString();
		String p = "";
		int o = 0;
		while (tmp.charAt(o) != ',') {
			p += tmp.charAt(o);
			o++;
		}
		return Integer.parseInt(p);
	}

	public static void main(String[] args) {
		// testing data
		FramePMEditTeam fst = new FramePMEditTeam(12, 3, true);
		fst.setVisible(true);
	}
}