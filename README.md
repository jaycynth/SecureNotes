# SecureNotes
SecureNotes is a modern Android application (MVI, Clean Architecture, Jetpack Compose) designed with a specific purpose: to demonstrate a robust **Shift Left** security pipeline. It shows how to implement an automated security gate that catches vulnerabilities *before* they reach production.

---

## Purpose
The primary goal of this project is to showcase a functional **CI/CD Security Gate**. By intentionally including common Android vulnerabilities, this repository proves how automated Static Analysis (SAST) can be used to enforce "Trigger Discipline" and "Fail-on-Critical" policies in a DevSecOps workflow.

## The Security Pipeline Architecture
Every code change follows a strictly enforced path to ensure the security posture of the application:

1.  **Code Push/PR**: Triggered on every push to `main` and every Pull Request.
2.  **SAST Execution**: GitHub Actions automatically spins up a runner to execute `mobsfscan`.
3.  **Severity Decision**: The gate is configured with a **Fail-on-Critical** policy. If vulnerabilities with "High" or "Critical" severity are detected, the workflow exits with a non-zero code.
4.  **Artifact Upload**: Regardless of success or failure, a full scan report (`mobsf-results.json`) is uploaded as a workflow artifact, providing a concrete audit trail.
5.  **Merge Protection**: GitHub Branch Protection rules prevent the PR from being merged until the security check passes.

## Why These Gates Exist
The pipeline targets specific, high-risk Android security pitfalls demonstrated in this project:

| Gate Check | Security Risk | Impact |
| :--- | :--- | :--- |
| **Secrets Detection** | Hardcoded API Keys/Secrets | Unauthorized access to backend services or user data. |
| **WebView Policy** | Unsafe WebView Configuration | Potential for Cross-Site Scripting (XSS) or local file access. |
| **Logging Check** | Sensitive Data in Logcat | PII or credentials leaked to system logs, accessible by other apps. |
| **Storage Security** | Plaintext SQLite/Room DB | Data theft from rooted devices or via backup exploits. |

## Version Navigation
The repository is structured into stages to show the transition from "Insecure" to "Hardened":

*   **Version 1 (Insecure Baseline)**: `git checkout v1.0-insecure`
    *   *State*: Full of vulnerabilities. The pipeline **fails** here.
*   **Version 2 (Pipeline Integrated)**: `git checkout v2.0-pipeline`
    *   *State*: The security gate is active and blocking merges.
*   **Version 3 (Secured & Merged)**: `git checkout v3.0-secured`
    *   *State*: Vulnerabilities remediated (Encrypted Room, Secure WebView, etc.). The pipeline **passes**.

## Developer Workflow

### Local Verification (Pre-Push)
To avoid pipeline failures and keep the "green build" streak, developers should run the scan locally before pushing:
```bash
pip install mobsfscan
mobsfscan . --exit --severity high
```

### Handling False Positives
If a finding is determined to be a false positive or an acceptable business risk:
1.  **Suppression**: Add the rule ID to a `.mobsf-ignore` file in the root directory.
2.  **Documentation**: Clearly document the reasoning in the PR description.
3.  **Approval**: Requires security sign-off or peer review before the suppression is accepted as part of the audit trail.

## What Proves Success?
The effectiveness of this DevSecOps implementation is evidenced by:
- **Workflow Runs**: Visit the **Actions** tab in this repository to see the history of passed and blocked builds.
- **Audit Evidence**: Download the `mobsf-security-scan-results` artifact from any workflow run to inspect the detailed findings and remediation advice.

---

### Project Stack
- **Architecture**: MVI, Clean Architecture, Dagger Hilt
- **UI**: Jetpack Compose
- **Database**: Room (Baseline: Plaintext | Secured: SQLCipher)
- **CI/CD**: GitHub Actions + mobsfscan
