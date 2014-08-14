hg log --rev . --template "Revision:{rev}\nChangeSet:{node}\n" > src/main/resources/revision.txt
echo BuildDate:%DATE%>> src/main/resources/revision.txt