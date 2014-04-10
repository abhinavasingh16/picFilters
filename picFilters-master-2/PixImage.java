/* PixImage.java */

/**
 *  The PixImage class represents an image, which is a rectangular grid of
 *  color pixels.  Each pixel has red, green, and blue intensities in the range
 *  0...255.  Descriptions of the methods you must implement appear below.
 *  They include a constructor of the form
 *
 *      public PixImage(int width, int height);
 *
 *  that creates a black (zero intensity) image of the specified width and
 *  height.  Pixels are numbered in the range (0...width - 1, 0...height - 1).
 *
 *  All methods in this class must be implemented to complete Part I.
 *  See the README file accompanying this project for additional details.
 */

public class PixImage {

  private int arrayWidth;
  private int arrayHeight;
  private short[][][] workingArray; 


  /**
   * PixImage() constructs an empty PixImage with a specified width and height.
   * Every pixel has red, green, and blue intensities of zero (solid black).
   *
   * @param width the width of the image.
   * @param height the height of the image.
   */
  public PixImage(int width, int height) {
    arrayWidth = width;
    arrayHeight = height;
    workingArray = new short[height][width][3];
  }

  /**
   * getWidth() returns the width of the image.
   *
   * @return the width of the image.
   */
  public int getWidth() {
    return this.arrayWidth;
  }

  /**
   * getHeight() returns the height of the image.
   *
   * @return the height of the image.
   */
  public int getHeight() {
    return this.arrayHeight;
  }

  /**
   * getRed() returns the red intensity of the pixel at coordinate (x, y).
   *
   * @param x the x-coordinate of the pixel.
   * @param y the y-coordinate of the pixel.
   * @return the red intensity of the pixel at coordinate (x, y).
   */
  public short getRed(int x, int y) {
    short red = workingArray[y][x][0];
    return red;
  }

  /**
   * getGreen() returns the green intensity of the pixel at coordinate (x, y).
   *
   * @param x the x-coordinate of the pixel.
   * @param y the y-coordinate of the pixel.
   * @return the green intensity of the pixel at coordinate (x, y).
   */
  public short getGreen(int x, int y) {
    short green = workingArray[y][x][1];
    return green;
  }

  /**
   * getBlue() returns the blue intensity of the pixel at coordinate (x, y).
   *
   * @param x the x-coordinate of the pixel.
   * @param y the y-coordinate of the pixel.
   * @return the blue intensity of the pixel at coordinate (x, y).
   */
  public short getBlue(int x, int y) {
    short blue = workingArray[y][x][2];
    return blue;
  }

  /**
   * setPixel() sets the pixel at coordinate (x, y) to specified red, green,
   * and blue intensities.
   *
   * If any of the three color intensities is NOT in the range 0...255, then
   * this method does NOT change any of the pixel intensities.
   *
   * @param x the x-coordinate of the pixel.
   * @param y the y-coordinate of the pixel.
   * @param red the new red intensity for the pixel at coordinate (x, y).
   * @param green the new green intensity for the pixel at coordinate (x, y).
   * @param blue the new blue intensity for the pixel at coordinate (x, y).
   */
  public void setPixel(int x, int y, short red, short green, short blue) {
    workingArray[y][x][0] = red;
    workingArray[y][x][1] = green;
    workingArray[y][x][2] = blue;
  }

  /**
   * toString() returns a String representation of this PixImage.
   *
   * This method isn't required, but it should be very useful to you when
   * you're debugging your code.  It's up to you how you represent a PixImage
   * as a String.
   *
   * @return a String representation of this PixImage.
   */

  private String stringShorts(short[] ints) {
      String s = "[  ";
      for (int i = 0; i < ints.length; i++) {
        s = s + Integer.toString(ints[i]) + "  ";
      }
      return s + "]";
    }
  
  public String toString() {
      short[][][] array = this.workingArray;
      String separator = ", ";
      StringBuffer result = new StringBuffer();

      // iterate over the first dimension
      for (int i = 0; i < array.length; i++) {
          // iterate over the second dimension
          for(int j = 0; j < array[i].length; j++){
              result.append(stringShorts(array[i][j]));
              result.append(separator);
          }
          // remove the last separator
          result.setLength(result.length() - separator.length());
          // add a line break.
          result.append("\n");
      }
      return result.toString();
  }
  

  /**
   * boxBlur() returns a blurred version of "this" PixImage.
   *
   * If numIterations == 1, each pixel in the output PixImage is assigned
   * a value equal to the average of its neighboring pixels in "this" PixImage,
   * INCLUDING the pixel itself.
   *
   * A pixel not on the image boundary has nine neighbors--the pixel itself and
   * the eight pixels surrounding it.  A pixel on the boundary has six
   * neighbors if it is not a corner pixel; only four neighbors if it is
   * a corner pixel.  The average of the neighbors is the sum of all the
   * neighbor pixel values (including the pixel itself) divided by the number
   * of neighbors, with non-integer quotients rounded toward zero (as Java does
   * naturally when you divide two integers).
   *
   * Each color (red, green, blue) is blurred separately.  The red input should
   * have NO effect on the green or blue outputs, etc.
   *
   * The parameter numIterations specifies a number of repeated iterations of
   * box blurring to perform.  If numIterations is zero or negative, "this"
   * PixImage is returned (not a copy).  If numIterations is positive, the
   * return value is a newly constructed PixImage.
   *
   * IMPORTANT:  DO NOT CHANGE "this" PixImage!!!  All blurring/changes should
   * appear in the new, output PixImage only.
   *
   * @param numIterations the number of iterations of box blurring.
   * @return a blurred version of "this" PixImage.
   */
  
  private static short findMedian(short[] a){
      Quicksort object = new Quicksort();
      object.sort(a);
      a = object.numbers;
      short n = (short)(a.length);
      if (a.length % 2 == 0){
        return (short)((a[n/2] + a[(n/2)-1]) / 2);
      } else{
        return (a[(n-1)/2]);
      }
    }

    private PixImage medianFilterSingle(){
      int copyImageHieght = this.getHeight();
      int copyImageWidth = this.getWidth();
      PixImage copyImage = new PixImage(copyImageWidth,copyImageHieght);
      for (int heightIndex = 0; heightIndex < this.getHeight(); heightIndex++){
        for(int widthIndex = 0; widthIndex < this.getWidth(); widthIndex++){
          short[][] neighborhood = getNeighbors(this,heightIndex,widthIndex);
          short[] redNeighbors = getRedNeighbors(neighborhood);
          short[] greenNeighbors = getGreenNeighbors(neighborhood);
          short[] blueNeighbors = getBlueNeighbors(neighborhood);
          short redValMedian = findMedian(redNeighbors);
          short greenValMedian = findMedian(greenNeighbors);
          short blueValMedian = findMedian(blueNeighbors);
          copyImage.setPixel(widthIndex,heightIndex,redValMedian,greenValMedian,blueValMedian);
        }
      }
      return copyImage;
    }

    public PixImage medianFilter(int numIterations){
      int numIter = numIterations;
      int copyImageHeight = this.getHeight();
      int copyImageWidth = this.getWidth();
      PixImage copyImage = new PixImage(copyImageWidth,copyImageHeight);
        
      for (int x = 0; x < this.getHeight();x++){
        for (int y = 0; y < this.getWidth();y++){
          copyImage.workingArray[x][y]=this.workingArray[x][y];
        }
      }
        
      if (numIterations <= 0) {
        return this;
      } else { 
          while (numIter > 0) {
            copyImage = copyImage.medianFilterSingle();
            numIter--;
          }
        return copyImage;
       }
     }

  private PixImage boxBlurSingle() {
    int copyImageHieght = this.getHeight();
    int copyImageWidth = this.getWidth();
    PixImage copyImage = new PixImage(copyImageWidth,copyImageHieght);
    for (int heightIndex = 0;heightIndex<this.getHeight();heightIndex++){
          for (int widthIndex = 0;widthIndex<this.getWidth();widthIndex++){
            short[][] neighborhood = getNeighbors(this,heightIndex,widthIndex);
            short[] redNeighbors = getRedNeighbors(neighborhood);
            short[] greenNeighbors = getGreenNeighbors(neighborhood);
            short[] blueNeighbors = getBlueNeighbors(neighborhood);
            short redV = 0;
            short greenV = 0;
            short blueV = 0;
            for (int b = 0;b<redNeighbors.length;b++) {
              redV = (short)(redV + redNeighbors[b]);
              greenV = (short)(greenV + greenNeighbors[b]);
              blueV = (short)(blueV + blueNeighbors[b]);
            }
            redV = (short)(redV/redNeighbors.length);
            greenV = (short)(greenV/greenNeighbors.length);
            blueV = (short)(blueV/blueNeighbors.length);
            copyImage.setPixel(widthIndex,heightIndex,redV,greenV,blueV);
          }
        }
    return copyImage;
  }
  
  public PixImage boxBlur(int numIterations) {
    int numIter = numIterations;
    int copyImageHeight = this.getHeight();
    int copyImageWidth = this.getWidth();
    PixImage copyImage = new PixImage(copyImageWidth,copyImageHeight);
      
    for (int x = 0; x < this.getHeight();x++){
      for (int y = 0; y < this.getWidth();y++){
        copyImage.workingArray[x][y]=this.workingArray[x][y];
      }
    }
      
    if (numIterations <= 0) {
      return this;
    } else { 
        while (numIter > 0) {
          copyImage = copyImage.boxBlurSingle();
          numIter--;
        }
      return copyImage;
     }
   }

  /**
   * mag2gray() maps an energy (squared vector magnitude) in the range
   * 0...24,969,600 to a grayscale intensity in the range 0...255.  The map
   * is logarithmic, but shifted so that values of 5,080 and below map to zero.
   *
   * DO NOT CHANGE THIS METHOD.  If you do, you will not be able to get the
   * correct images and pass the autograder.
   *
   * @param mag the energy (squared vector magnitude) of the pixel whose
   * intensity we want to compute.
   * @return the intensity of the output pixel.
   */
  
  private static short mag2gray(long mag) {
    short intensity = (short) (30.0 * Math.log(1.0 + (double) mag) - 256.0);

    // Make sure the returned intensity is in the range 0...255, regardless of
    // the input value.
    if (intensity < 0) {
      intensity = 0;
    } else if (intensity > 255) {
      intensity = 255;
    }
    return intensity;
  }

  
    private static String arrayToString(short[][] a) {

        String aString;     
        aString = "";
        int column;
        int row;

        for (row = 0; row < a.length; row++) {
            for (column = 0; column < a[0].length; column++ ) {
            aString = aString + " " + a[row][column];
            }
        aString = aString + "\n";
        }

        return aString;
    }


  /**
   * sobelEdges() applies the Sobel operator, identifying edges in "this"
   * image.  The Sobel operator computes a magnitude that represents how
   * strong the edge is.  We compute separate gradients for the red, blue, and
   * green components at each pixel, then sum the squares of the three
   * gradients at each pixel.  We convert the squared magnitude at each pixel
   * into a grayscale pixel intensity in the range 0...255 with the logarithmic
   * mapping encoded in mag2gray().  The output is a grayscale PixImage whose
   * pixel intensities reflect the strength of the edges.
   *
   * See http://en.wikipedia.org/wiki/Sobel_operator#Formulation for details.
   *
   * @return a grayscale PixImage representing the edges of the input image.
   * Whiter pixels represent stronger edges.
   */
  
 private static short[][] getNeighbors(PixImage image,int heightPos, int widthPos) {
      short[][][] array = image.workingArray;
      short[][] storeCorner = new short[4][3];
      short[][] storeEdge = new short[6][3];
      short[][] storeReg = new short[9][3];
      if (heightPos-1 < 0 || widthPos - 1 < 0 || heightPos + 1 >= image.getHeight() || widthPos + 1 >= image.getWidth()) {
        boolean isTL = (heightPos == 0 && widthPos == 0);
        boolean isTR = (heightPos == 0 && widthPos == image.getWidth()-1);
        boolean isBL = (heightPos == image.getHeight()-1 && widthPos == 0);
        boolean isBR = (heightPos == image.getHeight()-1 && widthPos == image.getWidth()-1);
        if (isTL || isTR || isBL || isBR) {
          if (isTL) {
            short[][] a = {array[0][0],array[0][1],array[1][0],array[1][1]};
            storeCorner = a;
            return storeCorner;
          } else if (isTR) {
            short[][] a = {array[0][image.getWidth()-2],array[0][image.getWidth()-1],array[1][image.getWidth()-2],array[1][image.getWidth()-1]};
            storeCorner = a;
            return storeCorner;           
          } else if (isBL) {
          short[][] a = {array[image.getHeight()-2][0],array[image.getHeight()-2][1],array[image.getHeight()-1][0],array[image.getHeight()-1][1]};
          storeCorner = a;
          return storeCorner;
          } else {
            short[][] a = {array[image.getHeight()-2][image.getWidth()-2],array[image.getHeight()-2][image.getWidth()-1],array[image.getHeight()-1][image.getWidth()-2],array[image.getHeight()-1][image.getWidth()-1]};
            storeCorner = a;
            return storeCorner;
          }
        }else {
          if (widthPos == 0){ //Then its a left edge
            short[][] b = {array[heightPos-1][0],array[heightPos-1][1],array[heightPos][0],array[heightPos][1],array[heightPos+1][0],array[heightPos+1][1]};
            storeEdge = b;
            return storeEdge;
          } else if (heightPos == 0){ //Then its the top edge
            short[][] b = {array[0][widthPos-1],array[0][widthPos],array[0][widthPos+1],array[1][widthPos-1],array[1][widthPos],array[1][widthPos+1]};
            storeEdge = b;
            return storeEdge;
          } else if (heightPos == image.getHeight()-1){ //Bottom edge
            short[][] b = {array[heightPos-1][widthPos-1],array[heightPos-1][widthPos],array[heightPos-1][widthPos+1],array[heightPos][widthPos-1],array[heightPos][widthPos],array[heightPos][widthPos+1]};
            storeEdge = b;
            return storeEdge;
          } else{ //Right edge
            short[][] b = {array[heightPos-1][widthPos-1],array[heightPos-1][widthPos],array[heightPos][widthPos-1],array[heightPos][widthPos],array[heightPos+1][widthPos-1],array[heightPos+1][widthPos]}; 
            storeEdge = b;
            return storeEdge;
          }
        }
      } else {
        short[][] c = {array[heightPos-1][widthPos-1],array[heightPos-1][widthPos],array[heightPos-1][widthPos+1],array[heightPos][widthPos-1],array[heightPos][widthPos],array[heightPos][widthPos+1],array[heightPos+1][widthPos-1],array[heightPos+1][widthPos],array[heightPos+1][widthPos+1]};
        storeReg = c;
        return storeReg;
      } 
    }

    private static short[] getRedNeighbors(short[][] neighborhood){
      int len = neighborhood.length;
      short[] values = new short[len];
      for (int i = 0;i<len;i++) {
        values[i] = neighborhood[i][0];
      }
      return values;
    }
    
    private static short[] getGreenNeighbors(short[][] neighborhood){
      int len = neighborhood.length;
      short[] values = new short[len];
      for (int i = 0;i<len;i++) {
        values[i] = neighborhood[i][1];
      }
      return values;
    }

    private static short[] getBlueNeighbors(short[][] neighborhood){
      int len = neighborhood.length;
      short[] values = new short[len];
      for (int i = 0;i<len;i++) {
        values[i] = neighborhood[i][2];
      }
      return values;
    }


  private PixImage imagePadder(){
      int imageHeight = this.getHeight();
      int imageWidth = this.getWidth();
      short[][][] image = this.workingArray;
      PixImage workingPic = new PixImage(imageWidth+2,imageHeight+2);
      short[][][] workingImage = workingPic.workingArray;
      
      for (int height = 1; height < workingImage.length-1; height++){
        for (int width = 1; width < workingImage[0].length-1;width++){
          workingImage[height][width] = image[height-1][width-1];
        }
      }

      workingImage[0][0] = image[0][0];
      workingImage[0][imageWidth+1] = image[0][imageWidth-1];
      workingImage[imageHeight+1][0] = image[imageHeight-1][0];
      workingImage[imageHeight+1][imageWidth+1] = image[imageHeight-1][imageWidth-1];

      for (int h = 1; h<=imageHeight;h++) {
        workingImage[h][0] = image[h-1][0];
      }

      for (int w = 1; w <= imageWidth; w++){
        workingImage[0][w] = image[0][w-1];
      }

      for (int a = 1; a<=imageHeight;a++) {
        workingImage[a][imageWidth+1] = image[a-1][imageWidth-1];
      }

      for (int b = 1; b <= imageWidth; b++){
        workingImage[imageHeight+1][b] = image[imageHeight-1][b-1];
      }
      return workingPic;
    }
      
  public PixImage applySepiaFilter() {
  // Play around with this. 20 works well and was recommended
  // by another developer. 0 produces black/white image
  int sepiaDepth = 20;

  int w = this.getWidth();
  int h = this.getHeight();


  // We need 3 integers (for R,G,B color values) per pixel.
  PixImage copyImage  = new PixImage(w,h);


  // Process 3 ints at a time for each pixel.
  // Each pixel has 3 RGB colors in array
    for (int height = 1; height < h; height++){
      for (int width = 1; width < w;width++){

      short r = this.getRed(width,height);
      short g = this.getGreen(width,height);
      short b = this.getGreen(width,height);

      short gry = (short)((r + g + b) / 3);
      r = g = b = gry;
      r = (short)(r + (sepiaDepth * 2));
      g = (short)(g + sepiaDepth);

      if (r>255) r=255;
      if (g>255) g=255;
      if (b>255) b=255;

      // Darken blue color to increase sepia effect
      b-= sepiaDepth;

      // normalize if out of bounds
      if (b<0) b=0;
      if (b>255) b=255;

      this.setPixel(width,height,r,g,b);
      
  }
}
    return this;
}

    public PixImage sobelEdges() {
      PixImage paddedPic = this.imagePadder();
      short[][][] paddedArray = paddedPic.workingArray;
      short[][][][] gradientSave = new short[this.getHeight()][this.getWidth()][3][2];

      short[] regDxOperator = {1,0,-1,2,0,-2,1,0,-1};
      short[] regDyOperator = {1,2,1,0,0,0,-1,-2,-1};
      for (int heightPosition = 1;heightPosition<=this.getHeight();heightPosition++){
        for (int widthPosition = 1; widthPosition <= this.getWidth();widthPosition++){
            short[][] regneighborhood = getNeighbors(paddedPic,heightPosition,widthPosition);
            short[] regRedList = getRedNeighbors(regneighborhood);
            short[] regGreenList = getGreenNeighbors(regneighborhood);
            short[] regBlueList = getBlueNeighbors(regneighborhood);
            short dxRedConvAccumreg = 0;
            short dxGreenConvAccumreg = 0;
            short dxBlueConvAccumreg = 0;
            short dyRedConvAccumreg = 0;
            short dyGreenConvAccumreg = 0;
            short dyBlueConvAccumreg = 0;
            for (int k = 0; k<9;k++){
              dxRedConvAccumreg = (short)(dxRedConvAccumreg + regDxOperator[k] * regRedList[k]);
              dxBlueConvAccumreg = (short)(dxBlueConvAccumreg + regDxOperator[k] * regBlueList[k]);
              dxGreenConvAccumreg = (short)(dxGreenConvAccumreg + regDxOperator[k] * regGreenList[k]);

              dyRedConvAccumreg = (short)(dyRedConvAccumreg + regDyOperator[k] * regRedList[k]);
              dyBlueConvAccumreg = (short)(dyBlueConvAccumreg + regDyOperator[k] * regBlueList[k]);
              dyGreenConvAccumreg = (short)(dyGreenConvAccumreg + regDyOperator[k] * regGreenList[k]);
            }
            gradientSave[heightPosition-1][widthPosition-1][0][0] = dxRedConvAccumreg;
            gradientSave[heightPosition-1][widthPosition-1][0][1] = dyRedConvAccumreg;
            gradientSave[heightPosition-1][widthPosition-1][1][0] = dxGreenConvAccumreg;
            gradientSave[heightPosition-1][widthPosition-1][1][1] = dyGreenConvAccumreg;
            gradientSave[heightPosition-1][widthPosition-1][2][0] = dxBlueConvAccumreg;
            gradientSave[heightPosition-1][widthPosition-1][2][1] = dyBlueConvAccumreg;
        }
      }

        //Calculate Energy 
        long[][] energySave = new long[this.getHeight()][this.getWidth()];
        for (int q = 0; q<this.getHeight();q++){
          for (int r = 0; r<this.getWidth();r++){
              long redDx = (long) gradientSave[q][r][0][0];
              long redDy = (long) gradientSave[q][r][0][1];
              long greenDx= (long) gradientSave[q][r][1][0];
              long greenDy= (long) gradientSave[q][r][1][1];
              long blueDx= (long) gradientSave[q][r][2][0];
              long blueDy= (long) gradientSave[q][r][2][1];
              long energy = (redDx * redDx) + (redDy * redDy) + (blueDx * blueDx) + (blueDy * blueDy) + (greenDx * greenDx) + (greenDy * greenDy);
              energySave[q][r] = energy;
          }
        }

        for (int s = 0; s<this.getHeight(); s++){
          for (int t = 0; t<this.getWidth();t++) {
            short pixel = mag2gray(energySave[s][t]);
            this.setPixel(t,s,pixel,pixel,pixel);
          }
        }

        return this;
      }




  /**
   * TEST CODE:  YOU DO NOT NEED TO FILL IN ANY METHODS BELOW THIS POINT.
   * You are welcome to add tests, though.  Methods below this point will not
   * be tested.  This is not the autograder, which will be provided separately.
   */


  /**
   * doTest() checks whether the condition is true and prints the given error
   * message if it is not.
   *
   * @param b the condition to check.
   * @param msg the error message to print if the condition is false.
   */
  private static void doTest(boolean b, String msg) {
    if (b) {
      System.out.println("Good.");
    } else {
      System.err.println(msg);
    }
  }

  /**
   * array2PixImage() converts a 2D array of grayscale intensities to
   * a grayscale PixImage.
   *
   * @param pixels a 2D array of grayscale intensities in the range 0...255.
   * @return a new PixImage whose red, green, and blue values are equal to
   * the input grayscale intensities.
   */
  private static PixImage array2PixImage(int[][] pixels) {
    int width = pixels.length;
    int height = pixels[0].length;
    PixImage image = new PixImage(width, height);

    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        image.setPixel(x, y, (short) pixels[x][y], (short) pixels[x][y],
                       (short) pixels[x][y]);
      }
    }
    return image;
  }

  /**
   * equals() checks whether two images are the same, i.e. have the same
   * dimensions and pixels.
   *
   * @param image a PixImage to compare with "this" PixImage.
   * @return true if the specified PixImage is identical to "this" PixImage.
   */
  public boolean equals(PixImage image) {
    int width = getWidth();
    int height = getHeight();

    if (image == null ||
        width != image.getWidth() || height != image.getHeight()) {
      return false;
    }

    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        if (! (getRed(x, y) == image.getRed(x, y) &&
               getGreen(x, y) == image.getGreen(x, y) &&
               getBlue(x, y) == image.getBlue(x, y))) {
          return false;
        }
      }
    }
    return true;
  }
}
