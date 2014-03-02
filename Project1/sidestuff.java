private PixImage iterBox(PixImage image) {
    //Establish New Image to be written on. 
    int copyImageHieght = image.arrayHeight;
    int copyImageWidth = image.arrayWidth;
    PixImage copyImage = new PixImage(copyImageWidth,copyImageHieght);

    //Process Corners
    short[] topLeftCornerData = getCornerSums(image,"Top Left");
    short[] topRightCornerData = getCornerSums(image,"Top Right");
    short[] bottomLeftCornerData = getCornerSums(image,"Botoom Left");
    short[] bottomRightCornerData = getCornerSums(image,"Bottom Right");

    copyImage.setPixel(0,0,topLeftCornerData[0],topLeftCornerData[1],topLeftCornerData[2]);
    copyImage.setPixel(0,image.getWidth()-1,topRightCornerData[0],topRightCornerData[1],topRightCornerData[2]);
    copyImage.setPixel(image.getHeight()-1,0,bottomLeftCornerData[0],bottomLeftCornerData[1],bottomLeftCornerData[2]);
    copyImage.setPixel(image.getHeight()-1,image.getWidth()-1,bottomRightCornerData[0],bottomRightCornerData[1],bottomRightCornerData[2]);

    //Process Left Side of the frame.
    for (int edgeIter = 1; edgeIter < image.getHeight()-1; edgeIter++){
      short redEdgeAccum = 0;
      short greenEdgeAccum = 0;
      short blueEdgeAccum = 0;
      int edgeIterCopy = edgeIter;
      for (int heightIndex = edgeIterCopy-1; heightIndex <= edgeIterCopy+1; heightIndex++){
        for (int widthIndex = 0; widthIndex <= 1; widthIndex++) {
          redEdgeAccum = (short) (redEdgeAccum + image.getRed(heightIndex,widthIndex));
          greenEdgeAccum = (short) (greenEdgeAccum + image.getGreen(heightIndex,widthIndex));
          blueEdgeAccum = (short) (blueEdgeAccum + image.getBlue(heightIndex,widthIndex));
        }
      }
      copyImage.setPixel(edgeIter,0,redEdgeAccum,greenEdgeAccum,blueEdgeAccum);
    }

    //Process the Top Side of the frame. 
    for (int edgeIter = 1; edgeIter < image.getWidth()-1; edgeIter++){
      short redEdgeAccum = 0;
      short greenEdgeAccum = 0;
      short blueEdgeAccum = 0;
      int edgeIterCopy = edgeIter;
      for (int heightIndex = 0; heightIndex <= 1; heightIndex++) {
        for (int widthIndex = edgeIterCopy - 1; widthIndex <= edgeIterCopy+1; widthIndex++) {
          redEdgeAccum = (short) (redEdgeAccum + image.getRed(heightIndex,widthIndex));
          greenEdgeAccum = (short) (greenEdgeAccum + image.getGreen(heightIndex,widthIndex));
          blueEdgeAccum = (short) (blueEdgeAccum + image.getBlue(heightIndex,widthIndex));
        }
      }
      copyImage.setPixel(0,edgeIter,redEdgeAccum,greenEdgeAccum,blueEdgeAccum);
    }

    //Process the Right Side of the frame.
    for (int edgeIter = 1; edgeIter < image.getHeight()-1; edgeIter++){
      short redEdgeAccum = 0;
      short greenEdgeAccum = 0;
      short blueEdgeAccum = 0;
      int edgeIterCopy = edgeIter;
      for (int heightIndex = edgeIterCopy-1; heightIndex <= edgeIterCopy+1; heightIndex++){
        for (int widthIndex = image.getWidth()-2; widthIndex < image.getWidth(); widthIndex++){
          redEdgeAccum = (short) (redEdgeAccum + image.getRed(heightIndex,widthIndex));
          greenEdgeAccum = (short) (greenEdgeAccum + image.getGreen(heightIndex,widthIndex));
          blueEdgeAccum = (short) (blueEdgeAccum + image.getBlue(heightIndex,widthIndex));
        }
      }
      copyImage.setPixel(edgeIter,image.getWidth()-1,redEdgeAccum,greenEdgeAccum,blueEdgeAccum);
    }

    //Process the Bottom Side of the frame. 
    for (int edgeIter = 1; edgeIter < image.getWidth()-1; edgeIter++) {
      short redEdgeAccum = 0;
      short greenEdgeAccum = 0;
      short blueEdgeAccum = 0;
      int edgeIterCopy = edgeIter;
      for (int heightIndex = image.getHeight()-2; heightIndex < image.getHeight(); heightIndex++) {
        for (int widthIndex = edgeIterCopy - 1; widthIndex <= edgeIterCopy + 1; widthIndex++) {
          redEdgeAccum = (short) (redEdgeAccum + image.getRed(heightIndex,widthIndex));
          greenEdgeAccum = (short) (greenEdgeAccum + image.getGreen(heightIndex,widthIndex));
          blueEdgeAccum = (short) (blueEdgeAccum + image.getBlue(heightIndex,widthIndex));
        }
      }
      copyImage.setPixel(edgeIter,image.getHeight()-1,redEdgeAccum,greenEdgeAccum,blueEdgeAccum);
    }  

    //Process the rest of the image 
    for (int heightIndex = 1; heightIndex < image.getHeight()-1; heightIndex++) {
      for (int widthIndex = 1; widthIndex < image.getWidth()-1; widthIndex++) {
        short redPixelData = image.getNeighborhoodData(image,heightIndex,widthIndex,"red");
        short greenPixelData = image.getNeighborhoodData(image,heightIndex,widthIndex,"green");
        short bluePixelData = image.getNeighborhoodData(image,heightIndex,widthIndex,"blue");
        copyImage.setPixel(heightIndex,widthIndex,redPixelData,greenPixelData,bluePixelData);
      }
    }
    return copyImage;
  }




      int count = 1;
    int indexCount = 0;
    int[] index = new int[red.length];
    for (int i = 1; i < red.length; i++) {
      if (red[i] == red[i-1] && blue[i] == blue[i-1] && green[i] == green[i-1]){
        count++;
        if (i == 1){
          index[0] = 0;
          index[1] = i;
          indexCount = 2;
          count = count+2;
        } else{
          index[indexCount] = i;
          indexCount++;
        }
      }
    }
    
    for (int j = 0; j < index.length; j++) {
      runLength = new DList2()
    }