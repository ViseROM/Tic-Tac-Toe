package manager;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import backend.MarkType;

/**
 * ImageManager class attempts to load image files and keeps track 
 * of them
 * 
 * @author Vachia Thoj
 *
 */
public class ImageManager 
{
	//For singleton
	private static ImageManager imageManager;
	
	//BufferedImages
	private BufferedImage titleImage;
	private BufferedImage[] buttonImages;
	private BufferedImage[] markImages;
	private BufferedImage[] smallMarkImages;
	
	
	/**
	 * Constructor (private)
	 */
	private ImageManager()
	{
		//Load image sheets
		this.titleImage = loadImage("/images/Title.png");
		BufferedImage buttonSheet = loadImage("/images/ButtonSheet.png");
		BufferedImage markSheet = loadImage("/images/MarkSheet.png");
		BufferedImage smallMarkSheet = loadImage("/images/SmallMarkSheet.png");
		
		loadButtonImages(buttonSheet);
		loadMarkImages(markSheet);
		loadSmallMarkImages(smallMarkSheet);
	}
	
	
	/**
	 * Method to be called to obtain ImageManager object (Singleton)
	 * @return ImageManager object 
	 */
	public static ImageManager instance()
	{
		if(imageManager == null)
		{
			imageManager = new ImageManager();
		}
		
		return imageManager;
	}
	
	/**
	 * Method that attempts to open/obtain an image
	 * 
	 * @param address (String) the address location of the image
	 * @return BufferedImage from the address provided, will return null if image cannot be found opened
	 */
	private BufferedImage loadImage(String address)
	{
		BufferedImage imageSheet = null;
		
		//Try to obtain an image
		try {
			//Obtain image from address
			imageSheet = ImageIO.read(getClass().getResourceAsStream(address));
			
			return imageSheet;
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("Error loading image");
			System.exit(0);
		}
		return null;
	}
	
	private void loadButtonImages(BufferedImage sheet)
	{
		buttonImages = new BufferedImage[10];
		int index = 0;
		
		for(int i = 0; i < 4; i++)
		{
			buttonImages[index] = sheet.getSubimage(i * 200, 0, 200, 50);
			++index;
		}
		
		for(int i = 0; i < 2; i++)
		{
			buttonImages[index] = sheet.getSubimage(i * 200, 50, 200, 50);
			++index;
		}
		
		for(int i = 0; i < 4; i++)
		{
			buttonImages[index] = sheet.getSubimage(i * 150, 100, 150, 50);
			++index;
		}
	}
	
	private void loadMarkImages(BufferedImage sheet)
	{
		markImages = new BufferedImage[2];
		
		for(int i = 0; i < 2; i++)
		{
			markImages[i] = sheet.getSubimage(i * 150, 0, 150, 150);
		}
	}
	
	private void loadSmallMarkImages(BufferedImage sheet)
	{
		smallMarkImages = new BufferedImage[2];
		
		for(int i = 0; i < 2; i++)
		{
			smallMarkImages[i] = sheet.getSubimage(i * 36, 0, 36, 36);
		}
	}
	
	//Getter methods
	public BufferedImage getTitleImage() {return titleImage;}
	public BufferedImage[] getButtonImages() {return buttonImages;}
	public BufferedImage[] getMarkImages() {return markImages;}
	public BufferedImage[] getSmallMarkImages() {return smallMarkImages;}
	
	public BufferedImage getMarkImage(MarkType markType)
	{
		switch(markType)
		{
			case X:
				return markImages[0];
			case O:
				return markImages[1];
			default:
				return null;
		}
	}
	
	public BufferedImage getSmallMarkImage(MarkType markType)
	{
		switch(markType)
		{
			case X:
				return smallMarkImages[0];
			case O:
				return smallMarkImages[1];
			default:
				return null;
		}
	}
}
