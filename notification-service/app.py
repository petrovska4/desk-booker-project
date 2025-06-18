from flask import Flask, request, jsonify

app = Flask(__name__)

@app.route('/notify', methods=['POST'])
def notify():
    data = request.get_json()
    user = data.get("user")
    action = data.get("action")
    message = f"Notification received for user {user} - Action: {action}"
    print(message)
    return jsonify({"message": message}), 200

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=5000)








