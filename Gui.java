import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.swing.border.*;

public class Gui extends JFrame{
	JPanel all, top, middle, bottom, mleft, mright, bleft, bmid, bright;
	JLabel l_top, original, filtered, l_filter, l_iterations, l_upload;
	JTextField iterations, upload;
	BufferedImage b_ori, b_fil;
	JComboBox dropdown;
	String filename;

	public Gui(int width, int height) {
		addJPanels();
		addComponents();

		Container content = getContentPane();
		content.add(all);
		setContentPane(content);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(width,height);
		setVisible(true);
	}

	public void addJPanels() {
		all = new JPanel();
		top = new JPanel();
		middle = new JPanel();
		bottom = new JPanel();
		mleft = new JPanel();
		mright = new JPanel();
		bleft = new JPanel();
		bmid = new JPanel();
		bright = new JPanel();

		all.setLayout(new BoxLayout(all, BoxLayout.Y_AXIS));
		middle.setLayout(new BoxLayout(middle, BoxLayout.X_AXIS));
		bottom.setLayout(new BoxLayout(bottom, BoxLayout.X_AXIS));
		bleft.setLayout(new BoxLayout(bleft,BoxLayout.X_AXIS));
		bmid.setLayout(new BoxLayout(bmid,BoxLayout.X_AXIS));
		bright.setLayout(new BoxLayout(bright,BoxLayout.X_AXIS));

		top.setMaximumSize(new Dimension(1500,50));
		middle.setMaximumSize(new Dimension(1500,600));
		bottom.setMaximumSize(new Dimension(1500,150));
		bleft.setMaximumSize(new Dimension(500, 150));
		bmid.setMaximumSize(new Dimension(500, 150));
		bright.setMaximumSize(new Dimension(500, 150));

		all.add(top);
		all.add(middle);
		all.add(bottom);
		middle.add(mleft);
		middle.add(mright);
		bottom.add(bleft);
		bottom.add(bmid);
		bottom.add(bright);

		top.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		middle.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		bottom.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		mleft.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		mright.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
	}

	public void addComponents() {
		//initialize top label
		l_top = new JLabel("Image Filtering");
		top.add(l_top, SwingConstants.CENTER);

		//initialize placement images
		filename = "balloon.tiff";
		b_ori = ImageUtils.readTIFF(filename);
		original = new JLabel(new ImageIcon(b_ori));
		b_fil = ImageUtils.readTIFF(filename);
		filtered = new JLabel(new ImageIcon(b_fil));
		mleft.add(original);
		mright.add(filtered);

		//initialize filter label and dropdown menu
		l_filter = new JLabel("Filter: ");
		bleft.add(l_filter);

		String[] options = {"None", "Blur", "Sobel edge", "Median", "Sepia"};
		dropdown = new JComboBox(options);
		dropdown.setSelectedIndex(0);
		dropdown.addActionListener(new UpdateListener());
		bleft.add(dropdown);

		//initialize iterations label and text field
		l_iterations = new JLabel("Iterations (for blur): ");
		bmid.add(l_iterations);
		iterations = new JTextField("1");
		iterations.setMaximumSize(new Dimension(500, 50));
		iterations.addActionListener(new UpdateListener());
		bmid.add(iterations);

		//initialize upload label and textfield
		l_upload = new JLabel("File name: ");
		bright.add(l_upload);
		upload = new JTextField("balloon.tiff");
		upload.setMaximumSize(new Dimension(500, 50));
		upload.addActionListener(new UpdateListener());
		bright.add(upload);
	}

	public void update() {
		String op = (String)dropdown.getSelectedItem();
		String temp = upload.getText();
		try {
			BufferedImage test = ImageUtils.readTIFF(temp);
			filename = temp;
		} catch (Exception e) {
			System.out.println("Invalid file name.");
		}

		PixImage pix_fil;
		if (op == "None") {
			b_ori = ImageUtils.readTIFF(filename);
			original = new JLabel(new ImageIcon(b_ori));
			b_fil = ImageUtils.readTIFF(filename);
			filtered = new JLabel(new ImageIcon(b_fil));
		} else if (op == "Blur") {
			b_ori = ImageUtils.readTIFF(filename);
			original = new JLabel(new ImageIcon(b_ori));
			int iter = 1;
			try {
				iter = Integer.parseInt(iterations.getText());
			} catch (Exception e) {
				System.out.println("Not a valid input");
			}
			pix_fil = ImageUtils.buffer2PixImage(b_ori);
			pix_fil = pix_fil.boxBlur(iter);
			b_fil = ImageUtils.pixImage2buffer(pix_fil);
			filtered = new JLabel(new ImageIcon(b_fil));
		} else if (op == "Sobel edge") {
			b_ori = ImageUtils.readTIFF(filename);
			original = new JLabel(new ImageIcon(b_ori));
			pix_fil = ImageUtils.buffer2PixImage(b_ori);
			pix_fil = pix_fil.sobelEdges();
			b_fil = ImageUtils.pixImage2buffer(pix_fil);
			filtered = new JLabel(new ImageIcon(b_fil));
		} else if (op == "Median") {
			b_ori = ImageUtils.readTIFF(filename);
			original = new JLabel(new ImageIcon(b_ori));
			int iter = 1;
			try {
				iter = Integer.parseInt(iterations.getText());
			} catch (Exception e) {
				System.out.println("Not a valid input");
			}
			pix_fil = ImageUtils.buffer2PixImage(b_ori);
			pix_fil = pix_fil.medianFilter(iter);
			b_fil = ImageUtils.pixImage2buffer(pix_fil);
			filtered = new JLabel(new ImageIcon(b_fil));
		} else if (op == "Sepia") {
			b_ori = ImageUtils.readTIFF(filename);
			original = new JLabel(new ImageIcon(b_ori));
			pix_fil = ImageUtils.buffer2PixImage(b_ori);
			pix_fil = pix_fil.applySepiaFilter();
			b_fil = ImageUtils.pixImage2buffer(pix_fil);
			filtered = new JLabel(new ImageIcon(b_fil));
		}
		mleft.removeAll();
		mright.removeAll();
		mleft.add(original);
		mright.add(filtered);
		mleft.revalidate();
		mright.revalidate();
		all.revalidate();
	}

	public class UpdateListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			update();
		}
	}

	public static void main(String[] args) {
		Gui window = new Gui(1500,800);
	}
}