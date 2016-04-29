/**
 * Jordan Marx
 * 
 * Class for cropping the words "We order the domestic people to insure justice"
 * from the sound file "preamble.wav"
 * Then join the words together and save them in a new sound file
 */


public class CropWords
{

  public static void main (String args[]) 
  {
    // Open and create a sound file
    String filename = FileChooser.pickAFile();
    Sound sound1 = new Sound (filename);

    // Crop the sounds
    Sound sound2 = cropSound(sound1, 0, 15730);
    Sound sound3 = cropSound(sound1, 58810, 65015);
    Sound sound4 = cropSound(sound1, 15730, 17407);
    Sound sound5 = cropSound(sound1, 143226, 152550);
    Sound sound6 = cropSound(sound1, 17407, 26726);
    Sound sound7 = cropSound(sound1, 63522, 66435);
    Sound sound8 = cropSound(sound1, 132656, 141912);
    Sound sound9 = cropSound(sound1, 116946, 132714);
    
    // Join the sounds
    Sound sound10 = joinSound(sound2, sound3);
    Sound sound11 = joinSound(sound10, sound4);
    Sound sound12 = joinSound(sound11, sound5);
    Sound sound13 = joinSound(sound12, sound6);
    Sound sound14 = joinSound(sound13, sound7);
    Sound sound15 = joinSound(sound14, sound8);
    Sound sound16 = joinSound(sound15, sound9);

    // Play the new joined sound
    sound16.play();

    // Save the new sound file
    String filename2 = FileChooser.pickAFile();
    sound3.write(filename2);
    
  }

  // Method for cropping sound
   public static Sound cropSound (Sound s, int startIndex, int endIndex)
  {
     // Difference between index
    int lenModified = endIndex - startIndex;
    
    // Sound between those index
    Sound sModified = new Sound (lenModified);
    SoundSample[] ssarrayM;
    ssarrayM = sModified.getSamples();
    
    SoundSample[] ssarray;
    ssarray = s.getSamples();
    
    int i;
    for (i = startIndex ; i < endIndex ; ++i)
    {
      int amplitude = ssarray[i].getValue();
      ssarrayM[i - startIndex].setValue (amplitude);
    }
    
    return  sModified;
  }
  
  // Method for joining two sounds
  public static Sound joinSound (Sound s1, Sound s2)
  {
    SoundSample[] ssarray;
    ssarray = s1.getSamples();
    
    SoundSample[] ssarray2;
    ssarray2 = s2.getSamples();
    
    int lenModified = s1.getLength() + s2.getLength();
    
    Sound sModified = new Sound (lenModified);
    SoundSample[] ssarrayM;
    ssarrayM = sModified.getSamples();
    
    System.out.println ("The sound has " + lenModified + " samples");
    System.out.println ("The array has " + ssarray.length + " position");
    
    int i;
    for (i = 0 ; i < s1.getLength() ; ++i)
    {
      int amplitude = ssarray[i].getValue();
      ssarrayM[i].setValue (amplitude);
    }
    
    for (i = 0 ; i < s2.getLength() ; ++i)
    {
      int amplitude = ssarray2[i].getValue();
      ssarrayM[ i + s1.getLength() ].setValue (amplitude);
    }
    
    return  sModified;
  }
  
  
}

