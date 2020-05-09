package com.masterarthur.pwlg;
import android.os.*;
import java.util.*;

public class TextGenerator
{
	public static abstract class TextListener {
		private int mCount;

		public TextListener()
		{
			
		}

		public void setCount(int mCount)
		{
			this.mCount = mCount;
		}

		public int getCount()
		{
			return mCount;
		}
		
		public abstract void onText(String text);
		public abstract void onError(Throwable err);
	}
	
	private static String[] sVerbs = {"Galloping", "Crying", "Fly", "Rise", "Blazing", "Reaching", "Searching", "Standing", "Guide", "Burn", "Climb", "Power", "Redeem", "Reflects", "Darkening", "Enloghtening"};
	private static String[] sAdverbs = {"triumphantly", "quickly", "eternally", "brigthly", "vengefully", "courageously", "windly", "frantically", "violently", "mysteriously", "bravely", "defiantly", "gracefully", "solemnly", "viciosly", "sorrowly"};
	private static String[] sPrepositions = {"through", "into", "above", "beneath", "amongst", "below", "under", "in", "against", "within", "inside", "before", "outside"};
	private static String[] sAdjectvies = {"snowy", "shining", "growing", "ancient", "rising", "crystal", "fantastical", "soulful", "agressive", "courageous", "defiant", "icy", "misty", "graceful", "bloody", "cloudy"};
	private static String[] sNouns = {"moonlight", "path", "ice", "darkness", "wings", "light", "skies", "dream", "clouds", "abyss", "fire", "stars", "lands", "hearts", "plains", "mountain", "night", "battle cry", "sun", "souls", "heavens", "destiny", "defendors", "fields", "sunlight"};
	
	private static String[][] sWords = {sVerbs, sAdverbs, sPrepositions, sAdjectvies, sNouns};
	
	private static Random sRandom = new Random();
	
	public static String getRandomItem(String [] items) {
		return items[sRandom.nextInt(items.length - 1)];
	}
	
	public static void generateLyrics(TextListener listener) {
		new AsyncTask<TextListener, String, String>() {
			TextListener mListener;
			
			
			@Override
			protected String doInBackground(TextListener... p1)
			{
				if(p1.length < 1)
					return null;
				mListener = p1[0];
				StringBuilder text = new StringBuilder();
				for(int i = 0; i < mListener.getCount(); i++) {
					for(int j = 0; j < sWords.length; j++) {
						text.append(getRandomItem(sWords[j]));
						if(j+1 == sWords.length) {
							text.append("\n");
						} else {
							text.append(" ");
						}
					}
					
					if(i%4 == 0 && i != 0) {
						text.append("\n");
					}
				}
				return text.toString();
			}
			
			@Override
			protected void onPostExecute(String res) {
				mListener.onText(res);
			}
			
		}.execute(listener);
	}
}
