pipeline {
    agent any
    
        options {
            timeout(10)
            gitLabConnection('GitLab')
        }
        triggers {
            gitlab(
                triggerOnPush: true,
                triggerOnMergeRequest: true,
                branchFilterType: 'All',
                addVoteOnMergeRequest: true)
        }

    stages {
        stage('Hello') {
            steps {
                echo 'Hello World'
            }
        }
    }
}
