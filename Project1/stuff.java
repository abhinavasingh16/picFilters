private short[] getEdgeSums(PixImage image,String whichEdge){
    short redEdgeAccum = 0;
    short greenEdgeAccum = 0;
    short blueEdgeAccum = 0;
    short[] meanAccumArray = new short[3];

    if (whichEdge  == "Left Edge") {
      //Process the Left Edge of the frame.
      for (int edgeIter = 1; edgeIter < image.getHeight()-1; edgeIter++){
        int edgeIterCopy = edgeIter;
        for (int heightIndex = edgeIterCopy-1; heightIndex <= edgeIterCopy+1; heightIndex++){
          for (int widthIndex = 0; widthIndex <= 1; widthIndex++) {
            redEdgeAccum = redEdgeAccum + image.getRed(heightIndex,widthIndex);
            greenEdgeAccum = greenEdgeAccum + image.getGreen(heightIndex,widthIndex);
            blueEdgeAccum = blueEdgeAccum + image.getBlue(heightIndex,widthIndex);
          }
        }
      }
    }

    if (whichEdge == "Top Edge") {
      //Process the Top Edge of the frame. 
      for (int edgeIter = 1; edgeIter < image.getWidth()-1; edgeIter++){
        int edgeIterCopy = edgeIter;
        for (int heightIndex = 0; heightIndex <= 1; heightIndex++) {
          for (int widthIndex = edgeIterCopy - 1; widthIndex <= edgeIterCopy+1; widthIndex++) {
            redEdgeAccum = redEdgeAccum + image.getRed(heightIndex,widthIndex);
            greenEdgeAccum = greenEdgeAccum + image.getGreen(heightIndex,widthIndex);
            blueEdgeAccum = blueEdgeAccum + image.getBlue(heightIndex,widthIndex);
          }
        }
      }
    }

    if (whichEdge == "Right Edge") {
      //Process the Right edge of the frame. 
      for (int edgeIter = 1; edgeIter < image.getHeight()-1; edgeIter++){
        int edgeIterCopy = edgeIter;
        for (int heightIndex = edgeIterCopy-1; heightIndex <= edgeIterCopy+1; heightIndex++){
          for (int widthIndex = image.getWidth-2; widthIndex < image.getWidth; widthIndex++){
            redEdgeAccum = redEdgeAccum + image.getRed(heightIndex,widthIndex);
            greenEdgeAccum = greenEdgeAccum + image.getGreen(heightIndex,widthIndex);
            blueEdgeAccum = blueEdgeAccum + image.getBlue(heightIndex,widthIndex);
          }
        }
      }
    }

    if(whichEdge == "Bottom Edge") {
      //Process the Bottom edge of the frame. 
      for (int edgeIter = 1; edgeIter < image.getWidth()-1; edgeIter++) {
        int edgeIterCopy = edgeIter;
        for (int heightIndex = image.getHeight()-2; heightIndex < image.getHeight(); heightIndex++) {
          for (int widthIndex = edgeIterCopy - 1; widthIndex <= edgeIterCopy + 1; widthIndex++) {
            redEdgeAccum = redEdgeAccum + image.getRed(heightIndex,widthIndex);
            greenEdgeAccum = greenEdgeAccum + image.getGreen(heightIndex,widthIndex);
            blueEdgeAccum = blueEdgeAccum + image.getBlue(heightIndex,widthIndex);
          }
        }
      }
    }

    meanAccumArray[0] = redEdgeAccum;
    meanAccumArray[1] = greenEdgeAccum;
    meanAccumArray[2] = blueEdgeAccum;

    return meanAccumArray; 

  }