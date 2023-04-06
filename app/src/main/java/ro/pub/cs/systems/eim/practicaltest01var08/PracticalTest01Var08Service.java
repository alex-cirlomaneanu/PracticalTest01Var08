package ro.pub.cs.systems.eim.practicaltest01var08;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.util.Random;

public class PracticalTest01Var08Service extends Service {
    private class MyThread extends Thread {
        private Context context;
        private Random random = new Random();

        private MyThread(Context context) {
            this.context = context;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(5000);
                    sendMessage();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        private void sendMessage() {
            Intent intent = new Intent();
            intent.setAction("ro.pub.cs.systems.eim.practicaltest01var08.action.string");
            String answer = intent.getStringExtra("answer");
            String newAnswer = new String();
            int pos = random.nextInt(answer.length());
            for (int i = 0; i < answer.length(); ++i) {
                if (i == pos) {
                    newAnswer += answer.charAt(i);
                } else {
                    newAnswer += '*';
                }
            }
            intent.putExtra("message", newAnswer);
            Log.d("[PracticalTest01Var08Service]", "Sending message: " + newAnswer);
            getApplicationContext().sendBroadcast(intent);
        }
    }

    private MyThread myThread = null;

    public PracticalTest01Var08Service() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (myThread == null) {
//            Context context = intent.getExtras().get("context");
            myThread = new MyThread(this);
            myThread.start();
        }
        return Service.START_REDELIVER_INTENT;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        try {
            myThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}