#!/usr/bin/env sh 

d=$(dirname $0)
git log -1 --pretty=format:Revision:%h > $d/src/main/resources/revision.txt
echo -n '\n' >> $d/src/main/resources/revision.txt
git log -1 --pretty=format:ChangeSet:%H >> $d/src/main/resources/revision.txt
echo -n '\n' >> $d/src/main/resources/revision.txt
date +BuildDate:%d.%m.%Y >> $d/src/main/resources/revision.txt
