from flask import Flask, request, jsonify
from youtube_transcript_api import YouTubeTranscriptApi

app = Flask(__name__)

@app.route('/transcript')
def transcript():

    video_id = request.args.get('videoId')

    try:

        ytt_api = YouTubeTranscriptApi()

        fetched_transcript = ytt_api.fetch(video_id)

        text = " ".join(
            [snippet.text for snippet in fetched_transcript]
        )

        return jsonify({
            "transcript": text
        })

    except Exception as e:

        return jsonify({
            "error": str(e)
        })

if __name__ == '__main__':
    app.run(port=5000)