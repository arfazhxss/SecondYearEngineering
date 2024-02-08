#!/bin/bash
#auto-git v6.5

find . -name ".DS_Store" -type f -delete
echo -e ".DS_Store\ncommit-hist.txt" > .gitignore
unEnScore=50
unEnString=$(printf '_%.0s' $(seq 1 "$unEnScore"))
echo -e "\n${unEnString}\n\n\t\tDELETE LOCAL CHANGES? (YES) \n\t\t\tOR\n\t\tPUSH LOCAL CHANGES (ENTER)\n"
read -s -n 3 -p "(yes/ENTER): " answer

if [[ $answer == "yes" || $answer == "Yes" || $answer == "YES" ]]; then
  echo -e "YES'ED\n${unEnString}"
  git stash
  git stash clear
  git pull
  echo -e "${unEnString}\n\t\tYour Repository is synced\n\t\twith the latest commit :)\n${unEnString}"
else
  echo -e "ENTER'ED\n${unEnString}"
  read -p "Your Commit Message: " commt

  if [[ -z "$commt" || ${#commt} -lt 3 ]]; then
    commt="Routine Commit"
    git add . && \
    git add -u && \
    git commit -m $"$commt"$'\nby @arfazhxss on '"$(date +'%a %d %b %Y')" && \
    git push origin HEAD
    git log > commit-hist.txt
    rm -Rf .DS_Store/
    echo -e "${unEnString}\n\t\tYour changes has been pushed\n\t\tto the repository :)\n${unEnString}"
  else
    git add . && \
    git add -u && \
    git commit -m $"$commt"$'\nCommit by @arfazhxss on '"$(date +'%a %d %b %Y')" && \
    git push origin HEAD
    git log > commit-hist.txt
    rm -Rf .DS_Store/
    echo -e "${unEnString}\n\t\tYour changes has been pushed\n\t\tto the repository :)\n${unEnString}"
  fi
fi
