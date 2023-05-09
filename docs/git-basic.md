# HEAD FIRST GIT

1. Create a root folder - startOver
1. Change to a root folder - cd startOver
1. Initialize git - git init
1. Create a git ignore file(help to ignore file which to be added to git repo) - touch .gitignore

## Git config

1. Config Global Fullname - git config --global user.name "Do Nguyen Ha"
1. Config Global Email - git config --global user.email "donguyenha@live.com"
1. Config Local Fullname - git config --local user.name "Do Nguyen Ha"
1. Config Local Email - git config --local user.email "donguyenha@live.com"
1. Show git config - git config --list

### Git branch

1. Branches allow you to keep your changes completely independent of one another.
1. Git branches can fork off and grow in parallel.
1. Create a branch - git branch alochym.
1. Switch to use a branch:
   1. git checkout alochym -> old git version.
   1. git switch alochym.
1. List all branches - git branch.
1. Delete branch - git branch -d alochym.
1. Rename branch - git branch -m dest-name source-name.

### Git merge

1. **Bringing the work that was done in separate branches together**.
1. Merging in Git typically involves two branches:
   1. The branch that you are on (we'll refer to this as the "proposer")
   1. The branch you wish to merge or "mix" in (we'll call this the "proposee").
1. Example:
   1. There are two branch: bake-cake and prepare-icing.
   1. You are on bake-cake branch - git switch bake-cake.
   1. You are going to merge prepare-icing to bake-cake - git merge prepare-icing.

### Git log

1. To visualize a the commits history and list all git commits in **current branch** - git log.
1. To navigate the git log we use up/down arrow key.
1. To quit the git log we use "q" key.
1. To make human read the git log - git log --pretty=oneline --abbrev-commit.
1. To navigate to specific commit of the git log - git log < commit-hash >.
1. To view commit history of file in the git log - git log < file-name >. 
1. How it works:
   - The current Git tree

     ```bash
            ------(B)------(C - your current branch)
           /
     ----(A)
           \
            ------(D)------(E)------(F)
     ```

   - **The git log start in (C) -> (B) -> (A - has no parent) -> Done**.

### Git diff

1. **To track the difference between the changes made on a file**.
1. How it works - **compares the version of Git's index with the version of the file in the working directory**.
1. **Any line prefixed with a "-" sign, that line belongs to the "a" (old) version of the README.md file in Git's index**.
1. **And line prefixed with a "+" sign, that line belongs to the "b" (new) version of the README.md file in current directory**. 

   ```bash
   [hadn@publish head-first-git]$ git diff
   diff --git a/head-first-git/README.md b/head-first-git/README.md
   index 1acff34..6eba707 100644
   --- a/head-first-git/README.md
   +++ b/head-first-git/README.md
   @@ -25,8 +25,8 @@
   
    1. **Bringing the work that was done in separate branches together**.
     1. Merging in Git typically involves two branches:
     -   1. The branch that you are on (we<E2><80><99>ll refer to this as the <E2><80><9C>proposer<E2><80><9D>)
     -   1. The branch you wish to merge or <E2><80><9C>mix<E2><80><9D> in (we<E2><80><99>ll call this the <E2><80><9C>proposee<E2><80><9D>).
     +   1. The branch that you are on (we'll refer to this as the "proposer")
     +   1. The branch you wish to merge or "mix" in (we'll call this the "proposee").
   ```

1. To compare the last `git add` to Git's index but **not do git commit yet** - git diff --cached.
1. To compare between 2 difference commits - git diff target-commit-ID source-commit-ID.
1. To compare between 2 difference branches - git diff target-branch source-branch.

### Git restore

1. To discard any changes in current directory:
   - git restore file-name-a file-name-b
   - git restore  --staged

### Git tag

1. Make a git tag - git tag v0.1
1. Make a git tag with commit ID - git tag v0.1 commit-ID