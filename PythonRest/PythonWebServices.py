from flask import Flask
from flask import jsonify
from flask import request
import py_eureka_client.eureka_client as eureka_client

your_rest_server_port = 5000

eureka_client.init(eureka_server="http://localhost:8090/eureka",
                   app_name="StudentFlask",
                   instance_port=your_rest_server_port)

app = Flask(__name__)

studentDB=[
 {
 'rollNo':'11',
 'name':'John Dennis',
 'section':'A'
 },
 {
 'rollNo':'12',
 'name':'Phil Coulson',
 'section':'B'
 }
 ]

@app.route("/welcome",methods=['GET'])
def welcome() :    
    return "Welcome to Python WebService"

@app.route("/student/getStudents",methods=['GET'])
def getStudents() :
    return jsonify({"stud":studentDB})

@app.route("/student/getStudent/<rollNo>",methods=['GET'])
def getStudentDetails(rollNo) :
    student = [stud for stud in studentDB if(stud['rollNo']==rollNo)]
    print(student)
    return jsonify({"stud":student})

@app.route("/student/updateStudent/<rollNo>",methods=['PUT'])
def updateStudentDetail(rollNo) :
    student = [stud for stud in studentDB if(stud['rollNo']==rollNo)]

    if('rollNo' in request.json) :
        print("Student Available")
    if('name' in request.json) :
        student[0]['name'] = request.json['name']
    return jsonify({"stud":student[0]})

@app.route("/student/addStudent",methods=['POST'])
def addStudent() :
    student = {
        'rollNo':request.json['rollNo'],
        'name':request.json['name'],
        'section':request.json['section']
    }
    studentDB.append(student)
    return jsonify({"stud":studentDB})

@app.route("/student/removeStudent/<rollNo>",methods=['DELETE'])
def removeStudent(rollNo) :
    student = [stud for stud in studentDB if(stud['rollNo']==rollNo)]
    if(len(student) > 0) :
        studentDB.remove(student[0])
    return jsonify({"stud":student})




if __name__=="__main__" :
    app.run()
