docker run -it \
  -p 8000:80 -e HOST='localhost' -e PORT=8000 \
	 --name todo_list \
	todolist:1