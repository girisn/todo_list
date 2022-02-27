rm -rf /tmp/todo_list
git clone https://github.com/girisn/todo_list.git /tmp/todo_list
cd /tmp/todo_list

cd todolist
mvn clean package -U
docker build -t todolist:1 .
