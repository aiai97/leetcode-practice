
## Distributed Lock

Redis, DB, and ZooKeeper can all be used for distributed locks, depending on the systems already used by the business. ZooKeeper provides stronger consistency, while Redis is suitable when traffic and performance are more sensitive.

The core idea is **resource uniqueness**: if the resource is already locked, do not process it again.

* **Timeout** → limits how long we wait
* **Backoff** → spaces out retries
* **Queue** → holds waiting tasks
* **Fail fast** → stops immediately if the lock is unavailable

Example 1: In an internal system, two users could modify the same policy at the same time. If the policy was locked, the system waited 10 seconds and retried. Since conflicts were rare, waiting and retrying was acceptable.

Example 2: In a user system, we needed to call LDAP and used staffId as a fallback. If the LDAP request failed because of a network issue, we did not retry. The update could fail this time, and the user could refresh the page later when the network was better. The business impact was low, so implementing retries was not worth the extra complexity.