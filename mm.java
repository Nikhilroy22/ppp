private void verifyTokenWithProgress(String token) {
    OkHttpClient client = new OkHttpClient();

    Request request = new Request.Builder()
            .url("https://yourdomain.com/api/user")
            .addHeader("Authorization", "Bearer " + token)
            .build();

    client.newCall(request).enqueue(new Callback() {
        @Override
        public void onFailure(Call call, IOException e) {
            runOnUiThread(() -> goToLogin());
        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {
            if (!response.isSuccessful()) {
                runOnUiThread(() -> goToLogin());
                return;
            }

            long contentLength = response.body().contentLength();
            InputStream inputStream = response.body().byteStream();

            byte[] buffer = new byte[2048];
            long totalBytesRead = 0;
            int read;

            while ((read = inputStream.read(buffer)) != -1) {
                totalBytesRead += read;

                final int percent = (int)((100 * totalBytesRead) / contentLength);
                runOnUiThread(() -> {
                    progressBar.setProgress(percent);
                    progressText.setText(percent + "%");
                });
            }

            inputStream.close();

            // শেষ হলে
            runOnUiThread(() -> goToDashboard());
        }
    });
}