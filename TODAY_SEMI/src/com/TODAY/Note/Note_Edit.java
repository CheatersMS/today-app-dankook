/*
 * Copyright (C) 2008 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.TODAY.Note;

import com.TODAY.R;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Note_Edit extends Activity {
    //private EditText mTitleText;
    private EditText mBodyText;
    private Long mRowId;
    private NotesDbAdapter mDbHelper;
	private Cursor mNotesCursor;
    
    private String day; //��,��,���� �޾ƿ��� day
    
    private boolean IsNew = true; 
    //��¥�� Ŭ�������� �޸� ����Ǿ����������� createNote()�����ϰ� �ƴϸ� 	updateNote()�����ϰ� �Ϸ��� ����
    //�⺻�� true
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDbHelper = new NotesDbAdapter(this);
        mDbHelper.open();        
        
		Intent i = getIntent();
        day = i.getStringExtra("day"); //��, ��, ���� ��Ʈ������ ����(day)
  
        setContentView(R.layout.note_edit);
       
        mBodyText = (EditText) findViewById(R.id.body);

        Button confirmButton = (Button) findViewById(R.id.confirm);
        Button cancelButton = (Button) findViewById(R.id.cancel);
        
        mRowId = (savedInstanceState == null) ? null :
            (Long) savedInstanceState.getSerializable(NotesDbAdapter.KEY_ROWID);
		if (mRowId == null) {
			Bundle extras = getIntent().getExtras();
			mRowId = extras != null ? extras.getLong(NotesDbAdapter.KEY_ROWID)
									: null;
		}
		

		// �̷��� ���پ��� �ȴ�.
//		Cursor cur = mDbHelper.fetchNoteByDay("2012-5-2");
//		if(cur.getCount() > 0)
//		{
//			Log.i("2012-5-2's Memo",cur.getString(cur.getColumnIndexOrThrow(NotesDbAdapter.KEY_BODY)));
//		}
		
		
		Log.i("mRowID!?", String.valueOf(mRowId));


		
		cancelButton.setOnClickListener(new View.OnClickListener() {
			//������ư �̺�Ʈ
            public void onClick(View view) {
            	mDbHelper.deleteNote(day);
                setResult(RESULT_OK);
                finish();
            }

        });
 
        confirmButton.setOnClickListener(new View.OnClickListener() {
        	//�����ư �̺�Ʈ
            public void onClick(View view) {
            	Log.i("MBody Context", mBodyText.getText().toString());
            	if(IsNew) 
            		mDbHelper.createNote(mBodyText.getText().toString(), day);
            	else
            		mDbHelper.updateNote(mBodyText.getText().toString(), day);
	       //������ �����ѵ��� IsNes�� �̿��ؼ� create, update����     	
                setResult(RESULT_OK);
                finish();
            	
            }
        });        
    }
    
    private void populateFields() {
        if (day != null) {				// Day�� ������ 2012-5-2���̴�.
            Cursor note = mDbHelper.fetchNoteByDay(day); 		// �ᱹ mDbHelper�� �̿��Ͽ� cursur�� ��� �ǰ�.. �� note�� �̿��ؼ� �ѷ��ְ� �ȴ�.
            Log.i("Day",day);
            if( note.getCount() > 0 )
            {
	            startManagingCursor(note);	            
	            mBodyText.setText(note.getString(note.getColumnIndexOrThrow(NotesDbAdapter.KEY_BODY)));
	            IsNew = false;
            }//day��  ��ȸ�ؼ� �޸� ������ ��� ����� body�� �ѷ��ְ�
        }	 //�ƴϸ� createNote()����(IsNew�� False�ϱ�)
    }

    @Override
    protected void onResume() { //Activity�� ���� ȣ��ɶ� ����
        super.onResume();
        
        populateFields();			// ���⸦ �׻� ���� �����ؼ� DB�� �ִ��� Ȯ���Ѵ�.
    }
    
    /*
	private void populateFields() {
        if (mRowId != null) {
            Cursor note = mDbHelper.fetchNote(mRowId);
            startManagingCursor(note);
           // mTitleText.setText(note.getString(
           //         note.getColumnIndexOrThrow(NotesDbAdapter.KEY_TITLE)));
            mBodyText.setText(note.getString(
                    note.getColumnIndexOrThrow(NotesDbAdapter.KEY_BODY)));
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) { 
    	//Activity �� stop �ǰ� ���� ���۵Ǳ� ���� Activity �� ���� �� ȣ��ȴ�.
    	super.onSaveInstanceState(outState);
        //saveState();
        outState.putSerializable(NotesDbAdapter.KEY_ROWID, mRowId);
    }

    @Override
    protected void onPause() { //Activity�� �����Ҷ� ȣ��
        super.onPause();
        saveState();
    }
    */
    /*
    private void saveState() { 
    //    String title = mTitleText.getText().toString();
        String body = mBodyText.getText().toString();
        
        
        if (mRowId == null) {
            long id = mDbHelper.createNote(body,day);
            if (id > 0) {
                mRowId = id;
            }
        } else {
            mDbHelper.updateNote(mRowId, body);
        }
    }
*/
}
