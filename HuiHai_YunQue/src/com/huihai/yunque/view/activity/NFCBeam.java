package com.huihai.yunque.view.activity;

import java.nio.charset.Charset;

import android.app.Activity;
import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.huihai.yunque.R;

public class NFCBeam extends Activity{
//        NfcAdapter mNfcAdapter;
//        TextView textView;
//
//        @Override
//        public void onCreate(Bundle savedInstanceState) {
//            super.onCreate(savedInstanceState);
//            setContentView(R.layout.nfc_view);
////            TextView textView = (TextView) findViewById(R.id.textView);
//            // Check for available NFC Adapter
//            mNfcAdapter = NfcAdapter.getDefaultAdapter(this);
//            if (mNfcAdapter == null) {
//                Toast.makeText(this, "NFC is not available", Toast.LENGTH_LONG).show();
////                finish();
////                return;
//            }
//            Button sendBtn = (Button) findViewById(R.id.send);
//            sendBtn.setOnClickListener(new OnClickListener() {
//                
//                @Override
//            public void onClick(View v) {
////                final Intent intent = new Intent(NfcAdapter.ACTION_TAG_DISCOVERED);
//                final Intent intent = new Intent(NfcAdapter.ACTION_NDEF_DISCOVERED);
//                intent.putExtra(NfcAdapter.EXTRA_NDEF_MESSAGES, createNdefMessage());
//                startActivity(intent);
//            }
//            });
//            // Register callback
////            mNfcAdapter.setNdefPushMessageCallback(this, this);
//        }
//
//        public NdefMessage createNdefMessage() {
//            String text = ("Beam me up, Android!\n\n" +
//                    "Beam Time: " + System.currentTimeMillis());
//            NdefMessage msg = new NdefMessage(
//                    new NdefRecord[] { createMimeRecord(
//                            "application/com.huihai.yunque.view.activity.NFCBeam", text.getBytes())
//             /**
//              * The Android Application Record (AAR) is commented out. When a device
//              * receives a push with an AAR in it, the application specified in the AAR
//              * is guaranteed to run. The AAR overrides the tag dispatch system.
//              * You can add it back in to guarantee that this
//              * activity starts when receiving a beamed message. For now, this code
//              * uses the tag dispatch system.
//              */
//              //,NdefRecord.createApplicationRecord("com.example.android.beam")
//            });
//            return msg;
//        }
//
//        @Override
//        public void onResume() {
//            super.onResume();
//            // Check to see that the Activity started due to an Android Beam
//            if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(getIntent().getAction())) {
//                processIntent(getIntent());
//            }
//        }
//
//        @Override
//        public void onNewIntent(Intent intent) {
//            // onResume gets called after this to handle the intent
//            setIntent(intent);
//        }
//        
////        void resolveIntent(Intent intent) {
////            // Parse the intent
////            String action = intent.getAction();
////            if (NfcAdapter.ACTION_TAG_DISCOVERED.equals(action)) {
////                // When a tag is discovered we send it to the service to be save. We
////                // include a PendingIntent for the service to call back onto. This
////                // will cause this activity to be restarted with onNewIntent(). At
////                // that time we read it from the database and view it.
////                Parcelable[] rawMsgs = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
////                NdefMessage[] msgs;
////                if (rawMsgs != null) {
////                    msgs = new NdefMessage[rawMsgs.length];
////                    for (int i = 0; i < rawMsgs.length; i++) {
////                        msgs[i] = (NdefMessage) rawMsgs[i];
////                    }
////                } else {
////                    // Unknown tag type
////                    byte[] empty = new byte[] {};
////                    NdefRecord record = new NdefRecord(NdefRecord.TNF_UNKNOWN, empty, empty, empty);
////                    NdefMessage msg = new NdefMessage(new NdefRecord[] {record});
////                    msgs = new NdefMessage[] {msg};
////                }
////                // Setup the views
////                setTitle(R.string.title_scanned_tag);
////                buildTagViews(msgs);
////            } else {
////                Log.e(TAG, "Unknown intent " + intent);
////                finish();
////                return;
////            }
////        }
//
//
//
//        /**
//         * Parses the NDEF Message from the intent and prints to the TextView
//         */
//        void processIntent(Intent intent) {
//            textView = (TextView) findViewById(R.id.plate_no);
//            Parcelable[] rawMsgs = intent.getParcelableArrayExtra(
//                    NfcAdapter.EXTRA_NDEF_MESSAGES);
//            // only one message sent during the beam
//            NdefMessage msg = (NdefMessage) rawMsgs[0];
//            // record 0 contains the MIME type, record 1 is the AAR, if present
//            textView.setText(new String(msg.getRecords()[0].getPayload()));
//        }
//
//        /**
//         * Creates a custom MIME type encapsulated in an NDEF record
//         */
//        public NdefRecord createMimeRecord(String mimeType, byte[] payload) {
//            byte[] mimeBytes = mimeType.getBytes(Charset.forName("US-ASCII"));
//            NdefRecord mimeRecord = new NdefRecord(
//                    NdefRecord.TNF_MIME_MEDIA, mimeBytes, new byte[0], payload);
//            return mimeRecord;
//        }
//    
}
