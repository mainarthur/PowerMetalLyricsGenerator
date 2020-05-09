package com.masterarthur.pwlg;

import android.app.*;
import android.os.*;
import android.widget.*;
import android.view.*;
import android.content.*;

public class MainActivity extends Activity implements View.OnClickListener, View.OnLongClickListener
{
	Button mGenerateButton;
	EditText mNumberEditText;
	TextView mResultTextView;
	
	TextGenerator.TextListener mListener;
	
	boolean mGeneratingFlag = false;
	
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		
		mGenerateButton = findViewById(R.id.mainGenerateButton);
		mResultTextView = findViewById(R.id.mainResTextView);
		mNumberEditText = findViewById(R.id.mainNumberEditText);
		
		mGenerateButton.setOnClickListener(this);
		mResultTextView.setOnLongClickListener(this);
		
		mListener = new TextGenerator.TextListener() {

			@Override
			public void onText(String text)
			{
				mResultTextView.setText(text);
				mGeneratingFlag = false;
			}

			@Override
			public void onError(Throwable err)
			{
				mResultTextView.setText("Error");
				mGeneratingFlag = false;
			}
	
		};
		
		onClick(mGenerateButton);
		
		
    }

	@Override
	public void onClick(View p1)
	{
		if(p1.getId() != mGenerateButton.getId() || mGeneratingFlag)
			return;
			
		int count = 10;
		
		try
		{
			count = Integer.parseInt(mNumberEditText.getText().toString());
		}
		catch (NumberFormatException e)
		{
			count = 10;
			mNumberEditText.setText("10");
		}
		
		
		if(count < 1) {
			count = 1;
			mNumberEditText.setText("1");
		}
		if(count > 1000) {
			//count = 1000;
			//mNumberEditText.setText("1000");
		}
		
		mListener.setCount(count);
		mGeneratingFlag = true;
		
		TextGenerator.generateLyrics(mListener);
	}

	@Override
	public boolean onLongClick(View p1)
	{
		if(p1.getId() != mResultTextView.getId())
			return false;
			
		Toast.makeText(this, "Text copied to clipboard",1).show();
			
		((ClipboardManager)getSystemService(CLIPBOARD_SERVICE)).setText(mResultTextView.getText().toString());
			
		return true;
	}


	

}
